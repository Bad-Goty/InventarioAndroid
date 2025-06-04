package com.example.inventario.presentation.components

import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.inventario.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QRScannerScreen(
    onQRScanned: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    when {
        cameraPermissionState.status.isGranted -> {
            CameraPreview(
                onQRScanned = onQRScanned,
                onBackPressed = onBackPressed
            )
        }
        else -> {
            // Pantalla de permisos
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Se necesita permiso de cámara para escanear códigos QR")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { cameraPermissionState.launchPermissionRequest() }
                ) {
                    Text("Conceder permiso")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onBackPressed) {
                    Text("Cancelar")
                }
            }
        }
    }
}

@Composable
fun CameraPreview(
    onQRScanned: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val executor = remember { Executors.newSingleThreadExecutor() }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                val previewView = PreviewView(ctx)
                val cameraProvider = cameraProviderFuture.get()

                // Preview
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                // Image Analysis para QR
                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(executor, QRCodeAnalyzer(onQRScanned))
                    }

                // Selector de cámara trasera
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (exc: Exception) {
                    // Manejar error
                }

                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
        // Botón de cerrar
        Button(
            onClick = onBackPressed,
            Modifier.padding(top = 25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)
        ) {
            Text("X")
        }
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {


            Image(painter = painterResource(R.drawable.logo), contentDescription = "Logo", Modifier.size(250.dp))

                Card(
                    modifier = Modifier.size(250.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = androidx.compose.ui.graphics.Color.Transparent
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        2.dp,
                        androidx.compose.ui.graphics.Color.White
                    )
                ) {}
                Text(
                    "Apunta al código QR",
                    color = androidx.compose.ui.graphics.Color.White,
                    fontWeight = FontWeight.Bold
                )

        }

    }
}

class QRCodeAnalyzer(
    private val onQRScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient()
    private var lastScannedTime = 0L

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        val currentTime = System.currentTimeMillis()
                        // Evitar escaneos duplicados muy rápidos
                        if (currentTime - lastScannedTime > 2000) {
                            barcode.rawValue?.let { qrContent ->
                                lastScannedTime = currentTime
                                onQRScanned(qrContent)
                            }
                        }
                    }
                }
                .addOnFailureListener {
                    // Manejar error si es necesario
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}