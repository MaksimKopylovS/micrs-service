import {Component, Injectable, OnDestroy} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {OAuthModule, OAuthService} from "angular-oauth2-oidc";
import {authConfig} from "./auth.config";
import {AppService} from "./app.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnDestroy{
  title = 'angular-oauth2';
  helloSubscription: Subscription;
  text = '';
  constructor(private oauthService: OAuthService, appService: AppService) {
    this.configure()
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: ['http://127.0.0.1:8073/v1'],
        sendAccessToken: true
      }
    })

    this.helloSubscription = appService.hello().subscribe(response => {
      this.text = response;
      console.log(this.text);
    })
  }

  private configure(){
    this.oauthService.configure(authConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }

  login(){
    this.oauthService.initCodeFlow()
  }

  logout(){
    this.oauthService.logOut()
  }

  ngOnDestroy(): void {
    this.helloSubscription.unsubscribe();
  }
}
