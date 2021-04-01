import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApplicationDetailReviewComponent } from './components/application-detail-review/application-detail-review.component';
import { ApplicationReviewComponent } from './components/application-review/application-review.component';
import { DocumentUploadComponent } from './components/document-upload/document-upload.component';
import { EmployeeHousingComponent } from './components/employee-housing/employee-housing.component';
import { HrHousingDetailComponent } from './components/hr-housing-detail/hr-housing-detail.component';
import { HrHousingComponent } from './components/hr-housing/hr-housing.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';


import { HrEmailComponent} from './components/hr-email/hr-email.component';


import { OnboardingComponent } from './components/onboarding/onboarding.component';
import { PageNotAuthorizedComponent } from './components/page-not-authorized/page-not-authorized.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { RegistrationComponent } from './components/registration/registration.component';

import { ReportDetailComponent } from './components/report-detail/report-detail.component';

import { TokenValidationComponent } from './components/token-validation/token-validation.component';

import { HrNavBarComponent } from './components/hr-nav-bar/hr-nav-bar.component';
import { PersonalInformationComponent } from './components/personal-information/personal-information.component';
import { EmployeeVisaStatusComponent } from './components/employee-visa-status/employee-visa-status.component';
import { VisaStatusManagementComponent } from './components/visa-status-management/visa-status-management.component';

import { ApplicationguardService } from './guards/applicationguard.service';
import { EmployeeguardService } from './guards/employeeguard.service';
import { HrguardService } from './guards/hrguard.service';

import { JWTGuardService } from './guards/jwtguard.service';
import { HrHomePageComponent } from './components/hr-home-page/hr-home-page.component';

import {ApplicationDetailReviewDocumentsComponent} from './components/application-detail-review-documents/application-detail-review-documents.component';
import { EmployeeHomePageComponent } from './components/employee-home-page/employee-home-page.component';
import { HrEmployeeProfileComponent } from './components/hr-employee-profile/hr-employee-profile.component';


const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"logout", component:LogoutComponent, canActivate:[JWTGuardService]},
  {path: "hrEmailSend", component:HrEmailComponent, canActivate:[JWTGuardService, EmployeeguardService]},
  {path:"registration", component:RegistrationComponent},
  {path:"documentUpload", component:DocumentUploadComponent, canActivate:[JWTGuardService, HrguardService]},
  {path:"personalInformation", component:PersonalInformationComponent, canActivate:[JWTGuardService]},
  {path:"visaStatusMgmt", component:EmployeeVisaStatusComponent, canActivate:[JWTGuardService, HrguardService, ApplicationguardService]},
  {path:"employeeHomePage", component:EmployeeHomePageComponent, canActivate:[JWTGuardService, HrguardService, ApplicationguardService]},

  {path: "hrVisaStatusManagement", component:VisaStatusManagementComponent, canActivate:[JWTGuardService, EmployeeguardService]},
  {
    path: "hrHomePage", component:HrHomePageComponent,
    canActivate:[JWTGuardService, EmployeeguardService]},
  {
    path: "hrEmployeeProfile",
    component: HrEmployeeProfileComponent,
    canActivate:[JWTGuardService, EmployeeguardService]
  },
  {
    path:"onboarding",
    component:OnboardingComponent,
    canActivate:[JWTGuardService, HrguardService]
  },
  {
    path:"employeeHousing",
    component:EmployeeHousingComponent,
    canActivate:[JWTGuardService, HrguardService, ApplicationguardService]
  },
  {
    path:"reportDetail/:reportId",
    component:ReportDetailComponent,
    canActivate:[JWTGuardService]
  },
  {
    path:"hrHousing",
    component:HrHousingComponent,
    canActivate:[JWTGuardService, EmployeeguardService]
  },
  {
    path:"hrHouseDetail/:hid",
    component:HrHousingDetailComponent,
    canActivate:[JWTGuardService, EmployeeguardService]
  },
  {
    path:"applicationReview",
    component:ApplicationReviewComponent,
    canActivate:[JWTGuardService, EmployeeguardService]
  },
  {
    path:"onboardingApplicationDetail/:aid",
    component:ApplicationDetailReviewComponent,
    canActivate:[JWTGuardService, EmployeeguardService]
  },
  {path:"pageNotAuthorized", component:PageNotAuthorizedComponent},
  {
    path:"onboardingApplicationDetail/Documents/:eid",
    component:ApplicationDetailReviewDocumentsComponent,
    canActivate:[JWTGuardService, EmployeeguardService]
  },
  {path:"**", component:PageNotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
