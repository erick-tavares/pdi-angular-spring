import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Usuario} from "./login/Usuario";
import {Observable} from "rxjs";

import { environment } from "../environments/environment";

import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl: string = environment.apiURL + "/usuarios"
  tokenUrl: string = environment.apiURL + environment.tokenUrl
  clientId:  string = environment.clientId
  clientSecret: string = environment.clientSecret
  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(
    private http: HttpClient
  ) { }

  obterToken(){
    const tokenString = localStorage.getItem('access_token')
    if(tokenString){
      const token = JSON.parse(tokenString).accessToken
      return token;
    }
    return null;
  }

  encerrarSessao(){
    localStorage.removeItem("access_token")
  }

  obterUsuarioAutenticado(){
    const token = this.obterToken();
    if(token){
      const usuario = this.jwtHelper.decodeToken(token).user_name;
      return usuario;
    }
    return null;
  }

  estaAutenticado(): boolean{
    const token = this.obterToken();
    if(token){
      const expirado = this.jwtHelper.isTokenExpired(token)
      return !expirado;
    }
    return false;
  }

  salvar(usuario : Usuario) : Observable<any> {
    return this.http.post<any>(this.apiUrl, usuario);
  }

  tentarLogar(username: string, password: string) : Observable<any>{
    const params = new HttpParams()
                        .set('username', username)
                        .set('password' , password)
                        .set('grant_type', 'password')

    const headers = {
      'Authorization' : 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
      'Content-Type' : 'application/x-www-form-urlencoded'
    }
    return this.http.post(this.tokenUrl, params.toString(), { headers });
  }
}
