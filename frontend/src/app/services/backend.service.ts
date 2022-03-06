import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private readonly baseUrl: string;

  constructor(private readonly http: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  public get<T>(url: string): Promise<T> {
    return firstValueFrom(this.http.get<T>(`${ this.baseUrl }${ url }`));
  }

  public post<T>(url: string, body: any): Promise<T> {
    return firstValueFrom(this.http.post<T>(`${ this.baseUrl }${ url }`, body));
  }

  public put<T>(url: string, body: any): Promise<T> {
    return firstValueFrom(this.http.put<T>(`${ this.baseUrl }${ url }`, body));
  }

  public delete<T>(url: string): Promise<T> {
    return firstValueFrom(this.http.delete<T>(`${ this.baseUrl }${ url }`));
  }
}
