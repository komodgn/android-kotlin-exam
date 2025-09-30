package com.sample.noti.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Neutral50 = Color(0xFFFAFAFA)
val Neutral100 = Color(0xFFF5F5F5)
val Neutral200 = Color(0xFFE5E5E5)
val Neutral300 = Color(0xFFD4D4D4)
val Neutral400 = Color(0xFFA1A1A1)
val Neutral500 = Color(0xFF737373)
val Neutral600 = Color(0xFF525252)
val Neutral700 = Color(0xFF404040)
val Neutral800 = Color(0xFF262626)
val Neutral900 = Color(0xFF171717)
val Neutral950 = Color(0xFF0A0A0A)

val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

val Red50 = Color(0xFFFFECEF)
val Red100 = Color(0xFFFFCED4)
val Red200 = Color(0xFFF59C9D)
val Red300 = Color(0xFFED7577)
val Red400 = Color(0xFFF85454)
val Red500 = Color(0xFFFF443A)
val Red600 = Color(0xFFF03939)
val Red700 = Color(0xFFDD2F33)
val Red800 = Color(0xFFD0272B)
val Red900 = Color(0xFFC2191F)

val Orange50 = Color(0xFFFFF8E0)
val Orange100 = Color(0xFFFFEDB0)
val Orange200 = Color(0xFFFFE17C)
val Orange300 = Color(0xFFFFD743)
val Orange400 = Color(0xFFFFCC00)
val Orange500 = Color(0xFFFFC300)
val Orange600 = Color(0xFFFFB500)
val Orange700 = Color(0xFFFFA100)
val Orange800 = Color(0xFFFF8F00)
val Orange900 = Color(0xFFFF6D00)

val Green50 = Color(0xFFF2FFF6)
val Green100 = Color(0xFFE3F8E9)
val Green200 = Color(0xFFC1E8CA)
val Green300 = Color(0xFF82C090)
val Green400 = Color(0xFF40BF5D)
val Green500 = Color(0xFF2F9647)
val Green600 = Color(0xFF257838)
val Green700 = Color(0xFF1C5A2A)
val Green800 = Color(0xFF123C1C)
val Green900 = Color(0xFF091D0E)

val Blue50 = Color(0xFFE3F4FF)
val Blue100 = Color(0xFFBBE2FF)
val Blue200 = Color(0xFF8DD0FF)
val Blue300 = Color(0xFF56BDFF)
val Blue400 = Color(0xFF1DADFF)
val Blue500 = Color(0xFF009EFF)
val Blue600 = Color(0xFF008FFF)
val Blue700 = Color(0xFF007BFF)
val Blue800 = Color(0xFF1269EC)
val Blue900 = Color(0xFF1F47CD)

val Kakao = Color(0xFFFBD300)
val Blank = Color(0xFFD6D6D6)
val MainBg = Color(0xFFF0F9E8)

@Immutable
data class NotiColorScheme(
    // Base
    val basePrimary: Color = White,
    val baseSecondary: Color = Neutral50,

    // Background
    val bgPrimary: Color = Orange500,
    val bgPrimaryPressed: Color = Orange600,
    val bgSecondary: Color = Neutral100,
    val bgSecondaryPressed: Color = Neutral200,
    val bgTertiary: Color = Orange100,
    val bgTertiaryPressed: Color = Orange200,
    val bgDisabled: Color = Neutral200,

    // Content
    val contentPrimary: Color = Neutral800,
    val contentSecondary: Color = Neutral500,
    val contentTertiary: Color = Neutral400,
    val contentBrand: Color = Orange500,
    val contentDisabled: Color = Neutral400,
    val contentInverse: Color = White,

    val contentError: Color = Red500,
    val contentInfo: Color = Blue500,
    val contentSuccess: Color = Green400,
    val contentWarning: Color = Orange300,

    // Border
    val borderPrimary: Color = Neutral200,
    val borderSecondary: Color = Neutral100,
    val borderBrand: Color = Orange500,
    val borderError: Color = Red500,
    val borderDisabled: Color = Neutral200,

    // Divider
    val dividerSm: Color = Neutral200,
    val dividerMd: Color = Neutral100,
)
