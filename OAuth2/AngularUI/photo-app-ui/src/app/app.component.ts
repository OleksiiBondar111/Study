import {Component} from '@angular/core';
import {AuthConfig, JwksValidationHandler, OAuthService} from "angular-oauth2-oidc";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:8080/realms/photoapp',
  tokenEndpoint: 'http://localhost:8080/realms/photoapp/protocol/openid-connect/token',
  redirectUri: window.location.origin,
  clientId: 'photo-app-PKCE-client',
  responseType: 'code',
  scope: 'openid profile',
};

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {
  title = 'photo-app-ui';


  constructor(private oauthService: OAuthService, private http: HttpClient, private router: Router) {
  }

  public logout() {
    this.oauthService.logOut();
  }

  getHelloText() {
    this.http.get<any[]>('http://localhost:8089/albums', {
      headers: {
        'Authorization': 'Bearer ' + this.oauthService.getAccessToken()
      }
    }).subscribe(res => console.log(res));
  }
}
