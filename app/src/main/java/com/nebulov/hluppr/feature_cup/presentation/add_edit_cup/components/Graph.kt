package com.nebulov.hluppr.feature_cup.presentation.add_edit_cup.components

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Graph(
    modifier: Modifier,
    xValues: List<Int>,
    yValues: List<Int>,
    points: List<Float>,
    paddingSpace: Dp,
    verticalStep: Float,
    changeSettings: State<Boolean>
) {
    val controlPoints1 = mutableListOf<PointF>()
    val controlPoints2 = mutableListOf<PointF>()
    val coordinates = mutableListOf<PointF>()
    val yAxisListName =
        listOf("Fragrance", "Flavor", "Aftertaste", "Acidity", "Body", "Balance", "Overall")
    val density = LocalDensity.current

    val listColor = listOf(
        MaterialTheme.colors.secondary,
        MaterialTheme.colors.primary
    )
    val primaryColor = MaterialTheme.colors.primary
    val onPrimaryColor = MaterialTheme.colors.onPrimary
    val onPrimaryColorInt = MaterialTheme.colors.onPrimary.toArgb()

    val textPaint = remember(density) {
        Paint().apply {
            color = onPrimaryColorInt
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }


    Surface(
        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 6.dp,
                end = 6.dp,
            ),
        color = primaryColor
    ) {
        Spacer(
            modifier = Modifier
                .padding(10.dp)
//                .aspectRatio(4 / 1f)
//                .fillMaxSize()
                .height(100.dp)
                .drawWithCache {
                    onDrawBehind {
                        val xAxisSpace = (size.width - paddingSpace.toPx()) / xValues.size
                        val yAxisSpace = size.height / yValues.size
                        /** placing x axis points */
                        /** placing x axis points */
                        for (i in xValues.indices) {
                            drawContext.canvas.nativeCanvas.drawText(
                                yAxisListName[i],
                                xAxisSpace * (i + 1),
                                size.height - 30,
                                textPaint
                            )
                        }
                        /** placing y axis points */
                        /** placing y axis points */
                        for (i in yValues.indices) {
                            drawContext.canvas.nativeCanvas.drawText(
                                "${yValues[i]}",
                                paddingSpace.toPx() / 2f,
                                size.height - yAxisSpace * (i + 1),
                                textPaint
                            )
                        }
                        /** placing our x axis points */
                        /** placing our x axis points */
                        for (i in points.indices) {
                            val x1 = xAxisSpace * xValues[i]
                            val y1 =
                                size.height - (yAxisSpace * (points[i] / verticalStep))
                            coordinates.add(PointF(x1, y1))
                            /** drawing circles to indicate all the points */
                            /** drawing circles to indicate all the points */
//                            if (!changeSettings.value)
                            drawCircle(
                                color = onPrimaryColor,
                                radius = 10f,
                                center = Offset(x1, y1)
                            )
//                            else
//                                drawContext.canvas.nativeCanvas.drawText(
//                                    String.format("%.2f", points[i] + 5), x1, y1, textPaint
//                                )
                        }
                        /** calculating the connection points */
                        /** calculating the connection points */
                        for (i in 1 until coordinates.size) {
                            controlPoints1.add(
                                PointF(
                                    (coordinates[i].x + coordinates[i - 1].x) / 2,
                                    coordinates[i - 1].y
                                )
                            )
                            controlPoints2.add(
                                PointF(
                                    (coordinates[i].x + coordinates[i - 1].x) / 2,
                                    coordinates[i].y
                                )
                            )
                        }
                        /** drawing the path */
                        /** drawing the path */
                        val stroke = Path().apply {
                            reset()
                            moveTo(coordinates.first().x, coordinates.first().y)
                            for (i in 0 until coordinates.size - 1) {
                                cubicTo(
                                    controlPoints1[i].x, controlPoints1[i].y,
                                    controlPoints2[i].x, controlPoints2[i].y,
                                    coordinates[i + 1].x, coordinates[i + 1].y
                                )
                            }
                        }
                        /** filling the area under the path */
                        /** filling the area under the path */
                        val fillPath = android.graphics
                            .Path(stroke.asAndroidPath())
                            .asComposePath()
                            .apply {
                                lineTo(xAxisSpace * xValues.last(), size.height - yAxisSpace)
                                lineTo(xAxisSpace, size.height - yAxisSpace)
                                close()
                            }
                        drawPath(
                            stroke,
                            color = primaryColor,
                            style = Stroke(
                                width = 5f,
                                cap = StrokeCap.Round
                            )
                        )
                        drawPath(
                            fillPath,
                            brush = Brush.verticalGradient(
                                listColor,
                                endY = size.height - yAxisSpace
                            )
                        )
                    }
                })
    }
}