import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Artikl } from 'src/app/models/artikl';
import { ArtiklService } from 'src/app/services/artikl.service';

@Component({
  selector: 'app-artikl-dialog',
  templateUrl: './artikl-dialog.component.html',
  styleUrls: ['./artikl-dialog.component.css']
})
export class ArtiklDialogComponent {

  flag!:number;

  constructor(public snackBar:MatSnackBar,
              public dialogRef: MatDialogRef<Artikl>,
              @Inject(MAT_DIALOG_DATA) public data: Artikl,
              public artiklService:ArtiklService){}


public add():void{
  this.artiklService.addArtikl(this.data).subscribe(
    () => {
      this.snackBar.open('Artikl sa nazivom: ' + this.data.naziv + ' je uspesno dodat!', 'Ok', {duration:4500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open('Dogodila se greska', 'Ok', {duration:2500});
  }
}

public update():void{
  this.artiklService.updateArtikl(this.data).subscribe(
    () => {
      this.snackBar.open('Artikl sa ID: ' + this.data.id + ' je uspesno izmenjen!', 'Ok', {duration:4500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open('Dogodila se greska', 'Ok', {duration:2500});
  }
}

public delete():void{
  this.artiklService.deleteArtikl(this.data.id).subscribe(
    () => {
      this.snackBar.open('Artikl je izbrisan!', 'Ok', {duration:4500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open('Dogodila se greska', 'Ok', {duration:2500});
  }
}

public cancel():void{
  this.dialogRef.close();
  this.snackBar.open('Odustali ste od izmena', 'Ok', {duration:2500})
}

}