import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Artikl } from 'src/app/models/artikl';
import { StavkaPorudzbine } from 'src/app/models/stavka-porudzbine';
import { ArtiklService } from 'src/app/services/artikl.service';
import { StavkaPorudzbineService } from 'src/app/services/stavka-porudzbine.service';

@Component({
  selector: 'app-stavka-porudzbine-dialog',
  templateUrl: './stavka-porudzbine-dialog.component.html',
  styleUrls: ['./stavka-porudzbine-dialog.component.css']
})
export class StavkaPorudzbineDialogComponent {

  flag!:number;
  artikli!: Artikl[];

  constructor(public snackBar:MatSnackBar,
              public dialogRef: MatDialogRef<StavkaPorudzbine>,
              @Inject(MAT_DIALOG_DATA) public data: StavkaPorudzbine,
              public stavkaPorudzbineService:StavkaPorudzbineService,
              public artiklService:ArtiklService){}

  ngOnInit(): void {
    this.artiklService.getAllArtikls().subscribe(
      data => {
        this.artikli = data;
      }
    )
  }


public add():void{
  this.stavkaPorudzbineService.addStavkaPorudzbine(this.data).subscribe(
    () => {
      this.snackBar.open('Stavka porudzbine je uspesno dodata!', 'Ok', {duration:4500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open('Dogodila se greska', 'Ok', {duration:2500});
  }
}

public update():void{
  this.stavkaPorudzbineService.updateStavkaPorudzbine(this.data).subscribe(
    () => {
      this.snackBar.open('Stavka porudzbine sa ID: ' + this.data.id + ' je uspesno izmenjena!', 'Ok', {duration:4500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open('Dogodila se greska', 'Ok', {duration:2500});
  }
}

public delete():void{
  this.stavkaPorudzbineService.deleteStavkaPorudzbine(this.data.id).subscribe(
    () => {
      this.snackBar.open('Stavka porudzbine je izbrisana!', 'Ok', {duration:4500});
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

public compare(a:any, b:any){
  return a.id == b.id;
}
}
