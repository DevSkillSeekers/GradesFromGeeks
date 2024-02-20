package com.solutionteam.mindfulmentor.ui.pdfReader

import androidx.lifecycle.ViewModel
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPdfReaderState
import org.koin.core.component.KoinComponent

class PDFReaderViewModel : ViewModel(), KoinComponent {

    val pdfVerticalReaderState = VerticalPdfReaderState(
        resource = ResourceType.Remote("https://myreport.altervista.org/Lorem_Ipsum.pdf"),
        isZoomEnable = true
    )
}
