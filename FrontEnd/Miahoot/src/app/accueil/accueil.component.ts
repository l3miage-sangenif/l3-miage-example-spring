import { ChangeDetectionStrategy, Component, OnInit, OnDestroy, Optional } from '@angular/core';
import { Auth, authState, signInAnonymously, signOut, User, GoogleAuthProvider, signInWithPopup } from '@angular/fire/auth';
import { EMPTY, Observable, Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import { traceUntilFirst } from '@angular/fire/performance';
import {Router} from '@angular/router';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AccueilComponent implements OnInit, OnDestroy {

  private readonly userDisposable: Subscription|undefined;
  public readonly user: Observable<User | null> = EMPTY;

  showLoginButton = false;
  showLogoutButton = false;
  connexion = false;
  

  constructor(@Optional() private auth: Auth, private router: Router) {
    if (auth) {
      this.user = authState(this.auth);
      this.userDisposable = authState(this.auth).pipe(
        traceUntilFirst('auth'),
        map(u => !!u)
      ).subscribe(isLoggedIn => {
        this.showLoginButton = !isLoggedIn;
        this.showLogoutButton = isLoggedIn;
      });
    }
  }

  ngOnInit(): void { }

  ngOnDestroy(): void {
    if (this.userDisposable) {
      this.userDisposable.unsubscribe();
    }
  }

  async login() {
    return await signInWithPopup(this.auth, new GoogleAuthProvider()).then((result)=>{
      console.log('User logged in');
      this.router.navigate(['/enseignant']);
    });    
  }

  async loginAnonymously() {
    return await signInAnonymously(this.auth);
  }

  async logout() {
    return await signOut(this.auth);
  }

  
}
