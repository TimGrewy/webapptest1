import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

  const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  const httpOptionsPost = {
    headers: new HttpHeaders({ 'Content-Type': 'text/plain' })
  };

@Injectable({
  providedIn: 'root'
})
export class TitleService {
  private listUrl = 'http://localhost/rest/scanner/list';  // URL to web api
  private titleUrl = 'http://localhost/rest/scanner/title';  // URL to web api

  //******************************************************************
  //CONSTRUCTOR
  //******************************************************************
  constructor(private http: HttpClient) { }

    //******************************************************************
    //PUBLIC METHODS
    //******************************************************************
    getTitles (): Observable<Object[]> {
      return this.http.get<Object[]>(this.listUrl)
          .pipe(
              catchError(this.handleError('getTitle', []))
            );
    }

    /** POST: add a new hero to the server */
    addTitle (title): Observable<Object> {
      return this.http.post(this.titleUrl, title, httpOptionsPost)
          .pipe(
            catchError(this.handleError<Object>('addTitle'))
          );
    }

    /** POST: add a new hero to the server */
    deleteTitle (title): Observable<Object> {
      return this.http.delete(this.titleUrl + "/" + title, httpOptionsPost)
          .pipe(
            catchError(this.handleError<Object>('deleteTitle'))
          );
    }

    //******************************************************************
    //PRIVATE METHODS
    //******************************************************************
    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T> (operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
        // TODO: send the error to remote logging infrastructure
        console.error(error); // log to console instead

        // Let the app keep running by returning an empty result.
        return of(result as T);
      };
    }
}
