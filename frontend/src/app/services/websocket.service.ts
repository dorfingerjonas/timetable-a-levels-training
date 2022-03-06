import { Injectable } from '@angular/core';
import { SocketClosed, WebSocket } from './utils';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private socket: WebSocket<string> | null;
  public sessionId: number | null;

  constructor() {
    this.socket = null;
    this.sessionId = null;
  }

  public sendMessage(message: string): void {
    if (this.socket === null || this.socket.closed) {
      throw new SocketClosed();
    }

    this.socket.sendMessage(message);
  }

  public connect(): Observable<string> {
    if (this.socket === null) {
      this.socket = new WebSocket<string>();
      this.sessionId = this.socket.sessionId;
      this.socket.errorMessages.subscribe(eMsg => console.log(`WebSocket error: ${ eMsg }`));
      this.socket.connect();
    }

    return this.socket.messages;
  }

  public close(): void {
    this.socket?.close();
  }
}
