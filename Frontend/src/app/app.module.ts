import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { HttpService } from './services/http.service';
import { CookieService } from 'ngx-cookie-service';

import { HrEmailComponent } from './components/hr-email/hr-email.component';
import { TokenValidationComponent } from './components/token-validation/token-validation.component';

import { HrNavBarComponent } from './components/hr-nav-bar/hr-nav-bar.component';

import { RegistrationComponent } from './components/registration/registration.component';
import { OnboardingComponent } from './components/onboarding/onboarding.component';
import { FileUploadService } from './services/file-upload.service';
import { DocumentUploadComponent } from './components/document-upload/document-upload.component';
import { DocumentDownloadLinkComponent } from './components/document-download-link/document-download-link.component';
import { JWTGuardService } from './guards/jwtguard.service';
import { EmployeeHousingComponent } from './components/employee-housing/employee-housing.component';
import { ReportDetailComponent } from './components/report-detail/report-detail.component';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { HrHousingComponent } from './components/hr-housing/hr-housing.component';

import { HrHomePageComponent } from './components/hr-home-page/hr-home-page.component';


import { HrHousingDetailComponent } from './components/hr-housing-detail/hr-housing-detail.component';
import { ApplicationReviewComponent } from './components/application-review/application-review.component';
import { ApplicationDetailReviewComponent } from './components/application-detail-review/application-detail-review.component';
import { EmployeeguardService } from './guards/employeeguard.service';
import { HrguardService } from './guards/hrguard.service';
import { ApplicationguardService } from './guards/applicationguard.service';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { PageNotAuthorizedComponent } from './components/page-not-authorized/page-not-authorized.component';

import { ApplicationDetailReviewDocumentsComponent } from './components/application-detail-review-documents/application-detail-review-documents.component';



import { NameSectionComponent} from './components/name-section/name-section.component';
import { PersonalInformationComponent } from './components/personal-information/personal-information.component'
import { AddressSectionComponent } from './components/address-section/address-section.component';
import { UserNavBarComponent } from './components/user-nav-bar/user-nav-bar.component';
import { EmployeeVisaStatusComponent } from './components/employee-visa-status/employee-visa-status.component';
import { PersonalDocDownloadComponent } from './components/personal-doc-download/personal-doc-download.component';
import { DocumentSectionComponent } from './components/document-section/document-section.component';
import { EmergencyContactSectionComponent } from './components/emergency-contact-section/emergency-contact-section.component';
import { EmploymentSectionComponent } from './components/employment-section/employment-section.component';
import { ContactInfoSectionComponent } from './components/contact-info-section/contact-info-section.component';
import { HrEmployeeProfileComponent } from './components/hr-employee-profile/hr-employee-profile.component';
import { VisaStatusManagementComponent } from './components/visa-status-management/visa-status-management.component';
import { EmployeeHomePageComponent } from './components/employee-home-page/employee-home-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,

    HrEmailComponent,
    TokenValidationComponent,
    HrNavBarComponent,

    TokenValidationComponent,
    RegistrationComponent,
    OnboardingComponent,
    NameSectionComponent,
    AddressSectionComponent,
    PersonalInformationComponent,
    UserNavBarComponent,
    DocumentUploadComponent,
    DocumentDownloadLinkComponent,

    EmployeeVisaStatusComponent,

    EmployeeHousingComponent,
    ReportDetailComponent,
    HrHousingComponent,

    HrHomePageComponent,

    HrHousingDetailComponent,
    ApplicationReviewComponent,
    ApplicationDetailReviewComponent,
    ApplicationDetailReviewDocumentsComponent,


    PersonalDocDownloadComponent,
    HrHousingDetailComponent,
    ApplicationReviewComponent,

    EmergencyContactSectionComponent,
    EmploymentSectionComponent,
    DocumentSectionComponent, 
    ContactInfoSectionComponent, 
    HrEmployeeProfileComponent,
    HrHomePageComponent,
    ApplicationDetailReviewComponent,
    VisaStatusManagementComponent,

    ApplicationDetailReviewComponent,
    PageNotFoundComponent,
    PageNotAuthorizedComponent,
    EmployeeHomePageComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],

  providers: [HttpService, CookieService, FileUploadService, JWTGuardService, EmployeeguardService, HrguardService, ApplicationguardService],

  bootstrap: [AppComponent]
})
export class AppModule { }
