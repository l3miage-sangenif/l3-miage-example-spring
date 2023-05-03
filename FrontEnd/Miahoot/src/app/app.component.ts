import { ChangeDetectionStrategy, Component, OnInit, OnDestroy, Optional } from '@angular/core';
import { Auth, authState, signInAnonymously, signOut, User, GoogleAuthProvider, signInWithPopup } from '@angular/fire/auth';
import { EMPTY, Observable, Subscription, async } from 'rxjs';
import { map } from 'rxjs/operators';
import { traceUntilFirst } from '@angular/fire/performance';
import {Router} from '@angular/router';
import { Location } from '@angular/common';

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

  showLoginButton = false ;
  showLogoutButton = false;
  photo : string | null = "";
  userId : string | undefined;
  deconnexionAnonyme = false;

  constructor(@Optional() private auth: Auth, private router: Router, private location: Location) {
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
      this.photo = result.user?.photoURL;
      this.userId = result.user.uid;
    });    
  }

  async loginParticipant() {
    return await signInWithPopup(this.auth, new GoogleAuthProvider()).then((result)=>{
      console.log('User logged in');
      this.router.navigate(['/participant']);
      this.photo = result.user.photoURL;
      this.userId = result.user.uid;
      
    });    
  }

  async loginAnonymously() {
    return await signInAnonymously(this.auth).then((result)=>{
      this.router.navigate(['/enseignant']);
      this.deconnexionAnonyme = ! this.deconnexionAnonyme;
    });
  }

  async logout() {
    return await signOut(this.auth).then((result)=>{
      console.log('User logged in');
      this.router.navigate(['/accueil']);
      this.showLogoutButton = !this.showLogoutButton;
      
    });    
  }

  goBack() {
    // window.history.back();
    this.location.back();

    console.log( 'goBack()...' );
  }

  
}

