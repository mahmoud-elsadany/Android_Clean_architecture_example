package com.swensonhe.presentation.viewmodel

import com.swensonhe.domain.usecases.landingpage.GetMLModelsTask
import com.swensonhe.presentation.base.BaseViewModel
import javax.inject.Inject

class LandingPageViewModel @Inject constructor(
    private val GetMLModelsTask: GetMLModelsTask,

    ) : BaseViewModel() {

}
