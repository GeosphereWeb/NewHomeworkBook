package de.geosphere.newhomeworkbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import de.geosphere.newhomeworkbook.R

private val fonts = FontFamily(
    Font(R.font.gruppo_regular),
)
//
// // Set of Material typography styles to start with
// val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp,
//    ),
//    /* Other default text styles to override
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
//
// )

val typography = typographyFromDefaults(
//    displayLarge = TextStyle(
//        fontFamily = fonts,
//        fontWeight = FontWeight.Bold,
//    ),
//    displayMedium = TextStyle(
//        fontFamily = fonts,
//        fontWeight = FontWeight.Bold,
//    ),
    werner = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
    )
)
fun typographyFromDefaults(
//    displayLarge: TextStyle?,
//    displayMedium: TextStyle?,
    werner: TextStyle,
): Typography {
    val defaults = Typography()
    return Typography(
//        displayLarge = defaults.displayLarge.merge(displayLarge),
//        displayMedium = defaults.displayMedium.merge(displayMedium),
    )
}
