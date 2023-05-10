import { ChangeDetectionStrategy, Component, OnInit, OnDestroy, Optional } from '@angular/core';
import { Auth, authState, signInAnonymously, signOut, User, GoogleAuthProvider, signInWithPopup } from '@angular/fire/auth';
import { EMPTY, Observable, Subscription, async } from 'rxjs';
import { map } from 'rxjs/operators';
import { traceUntilFirst } from '@angular/fire/performance';
import {Router} from '@angular/router';
import { Location } from '@angular/common';
import { EnseignantService } from './services/enseignant.service';
import { UserCreate } from './models/userCreate';
import { TransfertEnseignantQcmService } from './transfert-enseignant-qcm.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent implements OnInit, OnDestroy {

  userCreate: UserCreate= {
    uid:'123',
    nom: '',
    estEnseignant: false
  }

  title = 'Miahoot';
  private readonly userDisposable: Subscription|undefined;
  public readonly user: Observable<User | null> = EMPTY;

  showLoginButton = false ;
  showLogoutButton = false;
  photo : string | null = "";
  userId : string | undefined;
  deconnexionAnonyme = false;

  constructor(@Optional() private auth: Auth, private router: Router, private location: Location,private serviceUser: EnseignantService,private enseignantPartage: TransfertEnseignantQcmService) {
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

  ngOnInit(): void { 
  }

  ngOnDestroy(): void {
    if (this.userDisposable) {
      this.userDisposable.unsubscribe();
    }
  }

  async loginEnseignant() {
    return await signInWithPopup(this.auth, new GoogleAuthProvider()).then((result)=>{
      console.log('User logged in');
      this.router.navigate(['/enseignant']);
      this.photo = result.user.photoURL;
      this.userId = result.user.uid;
      this.enseignantPartage.idUser = this.userId;
      this.userCreate.estEnseignant = true;
      this.userCreate.nom = result.user.displayName as string;
      this.userCreate.uid = this.userId;
      this.serviceUser.createUser(this.userCreate).subscribe(response => {
        console.log({response});
      })
    });    
  }

  async loginParticipant() {
    return await signInWithPopup(this.auth, new GoogleAuthProvider()).then((result)=>{
      console.log('User logged in');
      this.router.navigate(['/participant']);
      this.photo = result.user.photoURL;
      this.userId = result.user.uid;
      this.enseignantPartage.idUser = this.userId;
      this.userCreate.estEnseignant = false;
      this.userCreate.nom = result.user.displayName as string;
      this.userCreate.uid = this.userId;
      this.serviceUser.createUser(this.userCreate).subscribe(response => {
        console.log({response});
      })      
    });    
  }

  async loginAnonymously() {
    return await signInAnonymously(this.auth).then((result)=>{
      this.router.navigate(['/enseignant']);
      this.userId = result.user.uid;
      this.enseignantPartage.idUser = this.userId;
      this.deconnexionAnonyme = ! this.deconnexionAnonyme;
      this.userCreate.estEnseignant = true;
      this.userCreate.uid = this.userId;
      this.serviceUser.createUser(this.userCreate).subscribe(response => {
        console.log({response});
      })
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

