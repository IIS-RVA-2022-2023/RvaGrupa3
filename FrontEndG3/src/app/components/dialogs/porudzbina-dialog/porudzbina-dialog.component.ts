import { DobavljacService } from './../../../services/dobavljac.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Dobavljac } from 'src/app/models/dobavljac';
import { Porudzbina } from 'src/app/models/porudzbina';
import { PorudzbinaService } from 'src/app/services/porudzbina.service';

@Component({
  selector: 'app-porudzbina-dialog',
  templateUrl: './porudzbina-dialog.component.html',
  styleUrls: ['./porudzbina-dialog.component.css']
})
export class PorudzbinaDialogComponent implements OnInit {

  flag!:number;
  dobavljaci!: Dobavljac[];

  constructor(public snackBar:MatSnackBar,
              public dialogRef: MatDialogRef<Porudzbina>,
              @Inject(MAT_DIALOG_DATA) public data: Porudzbina,
              public porudzbinaService:PorudzbinaService,
              public dobavljacService:DobavljacService){}

  ngOnInit(): void {
    this.dobavljacService.getAllDobavljacs().subscribe(
      data => {
        this.dobavljaci = data;
      }
    )
  }


public add():void{
  this.porudzbinaService.addPorudzbina(this.data).subscribe(
    () => {
      this.snackBar.open('Porudzbina sa ID: ' + this.data.id + ' je uspesno dodata!', 'Ok', {duration:4500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open('Dogodila se greska', 'Ok', {duration:2500});
  }
}

public update():void{
  this.porudzbinaService.updatePorudzbina(this.data).subscribe(
    () => {
      this.snackBar.open('Porudzbina sa ID: ' + this.data.id + ' je uspesno izmenjena!', 'Ok', {duration:4500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open('Dogodila se greska', 'Ok', {duration:2500});
  }
}

public delete():void{
  this.porudzbinaService.deletePorudzbina(this.data.id).subscribe(
    () => {
      this.snackBar.open('Porudzbina je izbrisana!', 'Ok', {duration:4500});
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
