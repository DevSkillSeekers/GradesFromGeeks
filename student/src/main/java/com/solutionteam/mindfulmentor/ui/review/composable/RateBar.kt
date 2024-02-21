import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.solutionteam.design_system.theme.Gold
import com.solutionteam.design_system.theme.Theme
import com.solutionteam.mindfulmentor.R


@Composable
fun RatingStar(
    rating: Float = 5f,
    maxRating: Int = 5,
    onStarClick: (Int) -> Unit,
    isIndicator: Boolean = false
) {

    Box(modifier = Modifier.padding(horizontal = 16.dp)){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Theme.colors.card, shape = RoundedCornerShape(16.dp))
                .padding(vertical = 13.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 1..maxRating) {
                if (i <= rating.toInt()) {
                    // Full stars
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Gold,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(!isIndicator) {
                                onStarClick(i)
                            }
                    )
                } else if (i == rating.toInt() + 1 && rating % 1 != 0f) {
                    // Partial star
                    PartialStar(fraction = rating % 1)
                } else {
                    // Empty stars
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_outline),
                        contentDescription = null,
                        tint = Gold,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(!isIndicator) {
                                onStarClick(i)
                            }
                    )
                }
            }
        }
    }
}

@Composable
private fun PartialStar(fraction: Float) {
    val customShape = FractionalClipShape(fraction)

    Box {
        Icon(
            painter = painterResource(id = R.drawable.ic_star_outline),
            contentDescription = null,
            tint = Gold,
            modifier = Modifier.size(24.dp)
        )
        Box(
            modifier = Modifier
                .graphicsLayer(
                    clip = true,
                    shape = customShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Gold,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


private class FractionalClipShape(private val fraction: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            rect = Rect(
                left = 0f,
                top = 0f,
                right = size.width * fraction,
                bottom = size.height
            )
        )
    }
}