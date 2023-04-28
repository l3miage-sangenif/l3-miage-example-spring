import { ChangeDetectionStrategy, Component, OnInit, OnDestroy, Optional } from '@angular/core';
import { Auth, authState, signInAnonymously, signOut, User, GoogleAuthProvider, signInWithPopup } from '@angular/fire/auth';
import { EMPTY, Observable, Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import { traceUntilFirst } from '@angular/fire/performance';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent implements OnInit, OnDestroy {

  title = 'Miahoot';
  private readonly userDisposable: Subscription|undefined;
  public readonly user: Observable<User | null> = EMPTY;

  showLoginButton : boolean;
  showLogoutButton = false;
  
  

  constructor(@Optional() private auth: Auth, private router: Router) {
    this.showLoginButton = false;
    if (auth) {
      console.log(this.user)
      this.showLoginButton = true;
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

  async loginEnseignant() {
    return await signInWithPopup(this.auth, new GoogleAuthProvider()).then((result)=>{
      console.log('User logged in');
      this.router.navigate(['/enseignant']);
      this.showLoginButton = !this.showLoginButton;
    });    
  }

  async loginParticipant() {
    return await signInWithPopup(this.auth, new GoogleAuthProvider()).then((result)=>{
      console.log('User logged in');
      this.router.navigate(['/participant']);
      this.showLoginButton = !this.showLoginButton;
    });    
  }

  async loginAnonymously() {
    return await signInAnonymously(this.auth);
  }

  async logout() {
    return await signOut(this.auth).then((result)=>{
      console.log('User logged in');
      this.router.navigate(['/accueil']);
      this.showLogoutButton = !this.showLogoutButton;
    });    
  }

  
}

