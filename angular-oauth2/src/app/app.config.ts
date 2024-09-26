import {ApplicationConfig, importProvidersFrom, provideZoneChangeDetection} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideOAuthClient} from "angular-oauth2-oidc";
import {provideHttpClient} from "@angular/common/http";
import {AppService} from "./app.service";

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideOAuthClient(),
    provideHttpClient(),
  ],
};
