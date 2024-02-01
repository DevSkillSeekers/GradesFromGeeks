package com.solutionteam.mindfulmentor.ui.onboarding

import com.solutionteam.mindfulmentor.R


data class OnBoardingData(
    val image: Int,
    val title: Int,
    val description: Int,
)

val sliderDataList = listOf(
        OnBoardingData(
                image = R.drawable.pc_first,
                title = R.string.Raise_your_grades_with_a_group_of_geeks,
                description = R.string.Raise_your_grades_with_a_group_of_geeks_description
        ),
        OnBoardingData(
                image = R.drawable.pc_second,
                title = R.string.Get_ready_for_a_lot_of_summaries,
                description = R.string.Get_ready_for_a_lot_of_summaries_description
        ),
        OnBoardingData(
                image = R.drawable.pc_third,
                title = R.string.Ask_experts_directly_and_get_answers,
                description = R.string.Ask_experts_directly_and_get_answers_description
        )
)
