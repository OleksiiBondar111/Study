import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthConfig, OAuthModule, OAuthService} from "angular-oauth2-oidc";
import {HttpClientModule} from "@angular/common/http";

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:8080/realms/photoapp',
  tokenEndpoint: 'http://localhost:8080/realms/photoapp/protocol/openid-connect/token',
  redirectUri: window.location.origin,
  clientId: 'photo-app-PKCE-client',
  responseType: 'code',
  scope: 'openid profile',
};

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    OAuthModule.forRoot()
  ],
  providers: [    {
    provide: APP_INITIALIZER,
    useFactory: (oauthService: OAuthService) => {
      return () => {
        initializeOAuth(oauthService);
      }
    },
    multi: true,
    deps: [
      OAuthService
    ]
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
function initializeOAuth(oAuthService: OAuthService): Promise<void> {
  return new Promise<void>((resolve, reject) => {
    oAuthService.configure(authCodeFlowConfig);
    oAuthService.setupAutomaticSilentRefresh();
    oAuthService.loadDiscoveryDocumentAndLogin().then(() => resolve())
  })

}
