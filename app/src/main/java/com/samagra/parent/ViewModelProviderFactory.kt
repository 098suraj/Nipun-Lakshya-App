package com.samagra.parent

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.data.repository.MetadataRepository
import com.data.repository.SchoolsRepository
import com.data.repository.StudentsRepository
import com.samagra.commons.basemvvm.BaseRepository
import com.samagra.parent.authentication.AuthenticationRepository
import com.samagra.parent.authentication.AuthenticationVM
import com.samagra.parent.authentication.OTPViewVM
import com.samagra.parent.repository.ExaminerPerformanceInsightsRepository
import com.samagra.parent.repository.MentorPerformanceInsightsRepository
import com.samagra.parent.repository.TeacherPerformanceInsightsRepository
import com.samagra.parent.ui.DataSyncRepository
import com.samagra.parent.ui.assessmenthome.AssessmentHomeVM
import com.samagra.parent.ui.assessmentsetup.AssessmentSetupVM
import com.samagra.parent.ui.competencyselection.CompetencySelectionVM
import com.samagra.parent.ui.detailselection.DetailsSelectionVM
import com.samagra.parent.ui.dietmentorassessmenttype.DIETAssessmentTypeVM
import com.samagra.parent.ui.userselection.UserSelectionRepository
import com.samagra.parent.ui.userselection.UserSelectionVM

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory(
    val application: Application,
    private val repository: BaseRepository
) : ViewModelProvider.Factory {
    private lateinit var repository1: BaseRepository
    private lateinit var repository2: BaseRepository
    private lateinit var repository3: BaseRepository

    constructor(
        application: Application,
        repository: BaseRepository,
        repository1: BaseRepository
    ) : this(application, repository) {
        this.repository1 = repository1
    }

    constructor(
        application: Application,
        repository: BaseRepository,
        repository1: BaseRepository,
        repository2: BaseRepository
    ) : this(application, repository, repository1) {
        this.repository1 = repository1
        this.repository2 = repository2
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AssessmentSetupVM::class.java) -> {
                return AssessmentSetupVM(
                    application,
                    repository as SchoolsRepository
                ) as T
            }
            modelClass.isAssignableFrom(CompetencySelectionVM::class.java) -> {
                return CompetencySelectionVM(
                    application,
                    repository1 as DataSyncRepository,
                    repository2 as MetadataRepository
                ) as T
            }
            modelClass.isAssignableFrom(UserSelectionVM::class.java) -> {
                return UserSelectionVM(
                    application,
                    repository as UserSelectionRepository,
                ) as T
            }
            modelClass.isAssignableFrom(DIETAssessmentTypeVM::class.java) -> {
                return DIETAssessmentTypeVM(
                    application,
                    repository as DataSyncRepository
                ) as T
            }
            modelClass.isAssignableFrom(DetailsSelectionVM::class.java) -> {
                return DetailsSelectionVM(
                    application,
                    repository as DataSyncRepository,
                    repository1 as MetadataRepository,
                    repository2 as StudentsRepository
                ) as T
            }
            modelClass.isAssignableFrom(OTPViewVM::class.java) -> {
                return OTPViewVM(
                    application,
                    repository as AuthenticationRepository
                ) as T
            }
            modelClass.isAssignableFrom(AuthenticationVM::class.java) -> {
                return AuthenticationVM(
                    application,
                    repository as AuthenticationRepository,
                    repository1 as StudentsRepository
                ) as T
            }
            modelClass.isAssignableFrom(AssessmentHomeVM::class.java) -> {
                return AssessmentHomeVM(
                    application,
                    repository as DataSyncRepository,
                    repository1 as TeacherPerformanceInsightsRepository,
                    repository2 as ExaminerPerformanceInsightsRepository,
                    repository3 as MentorPerformanceInsightsRepository,
                    null
                ) as T
            }
            else -> {
                throw IllegalArgumentException("View Model class not initialized in Factory : ${modelClass.name}")
            }
        }
    }
}