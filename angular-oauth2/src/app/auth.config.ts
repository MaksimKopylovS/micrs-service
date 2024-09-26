import {AuthConfig} from "angular-oauth2-oidc";

export const authConfig: AuthConfig = {
  issuer: "http://localhost:8068/realms/spmia-realm",
  redirectUri: 'http://localhost:4200/',
  clientId: 'oauth2-demo-pkce-client',
  responseType: 'code',
  strictDiscoveryDocumentValidation: true,
  scope: 'openid profile email offline_access',
}
