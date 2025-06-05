package com.forkial.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Electric Green and Dark Grey color definitions
private val ElectricGreen = Color(0xFF39FF14)
private val DarkGreyPrimaryText = Color(0xFF212121) // For text on ElectricGreen
private val DarkGreyBackground = Color(0xFF121212)
private val DarkGreySurface = Color(0xFF1E1E1E)
private val LightGreyText = Color(0xFFE0E0E0) // For text on DarkGreyBackground/DarkGreySurface
private val MidGreyOutline = Color(0xFF888888)

// Alternative Greens and Greys for containers and variants
private val MutedGreen = Color(0xFF2E8B57) // Sea Green, as an example for container
private val DarkerGreyContainer = Color(0xFF2C2C2C) // Slightly lighter than surface for variation
private val LightGreyContainer = Color(0xFFD3D3D3) // Light grey for light theme containers

// Standard Material Colors
private val MaterialError = Color(0xFFB00020)
private val MaterialOnError = Color.White

private val DarkColorPalette = darkColorScheme(
    primary = ElectricGreen,
    onPrimary = DarkGreyPrimaryText,
    primaryContainer = MutedGreen, // Or DarkerGreyContainer
    onPrimaryContainer = LightGreyText, // Or ElectricGreen if PrimaryContainer is dark grey
    secondary = MutedGreen, // Example, can be another shade of grey or a different accent
    onSecondary = DarkGreyPrimaryText,
    secondaryContainer = DarkerGreyContainer,
    onSecondaryContainer = LightGreyText,
    tertiary = ElectricGreen, // Can be a different accent if needed
    onTertiary = DarkGreyPrimaryText,
    tertiaryContainer = MutedGreen,
    onTertiaryContainer = LightGreyText,
    error = MaterialError,
    onError = MaterialOnError,
    background = DarkGreyBackground,
    onBackground = LightGreyText,
    surface = DarkGreySurface,
    onSurface = LightGreyText,
    surfaceVariant = DarkerGreyContainer,
    onSurfaceVariant = LightGreyText,
    outline = MidGreyOutline
)

// Example LightColorPalette - can be refined later
private val LightColorPalette = lightColorScheme(
    primary = ElectricGreen,
    onPrimary = DarkGreyPrimaryText,
    primaryContainer = LightGreyContainer, // A light green or light grey
    onPrimaryContainer = DarkGreyPrimaryText,
    secondary = MutedGreen,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE8F5E9), // Light green variant
    onSecondaryContainer = DarkGreyPrimaryText,
    tertiary = ElectricGreen,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFC8E6C9), // Light green variant
    onTertiaryContainer = DarkGreyPrimaryText,
    error = MaterialError,
    onError = Color.White,
    background = Color.White,
    onBackground = DarkGreyPrimaryText,
    surface = Color(0xFFF5F5F5), // Off-white
    onSurface = DarkGreyPrimaryText,
    surfaceVariant = LightGreyContainer,
    onSurfaceVariant = DarkGreyPrimaryText,
    outline = MidGreyOutline

    /* Other default colors to override if needed:
    surfaceTint = primary,
    inversePrimary = primary, // A color that contrasts well with primary
    inverseSurface = onSurface,
    inverseOnSurface = surface,
    scrim = Color.Black,
    */
)

val Typography = Typography(
    // Default Material 3 font styles. Customize as needed.
    // Example:
    // bodyLarge = TextStyle(
    //    fontFamily = FontFamily.Default,
    //    fontWeight = FontWeight.Normal,
    //    fontSize = 16.sp,
    //    lineHeight = 24.sp,
    //    letterSpacing = 0.5.sp
    // )
)

@Composable
fun ForkialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // dynamicColor: Boolean = true, // Set to false if not supporting dynamic color for now
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        //     val context = LocalContext.current
        //     if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        // }
        darkTheme -> DarkColorPalette
        else -> LightColorPalette // For now, we can use this or a more refined light theme
                                 // Defaulting to DarkColorPalette as per Forkial's design preference
                                 // else -> DarkColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
