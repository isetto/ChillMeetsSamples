package com.example.ad.retrofittest.Common_Clases.ErrorHandler;

import android.content.Context;
import android.widget.Toast;

public class ErrorTypes {

    public static void checkType (Context context, String code, String message){
        switch(code){

                //LOGIN
            case "BADPASS":
                Toast.makeText(context, "Podano niewłaściwe hasło", Toast.LENGTH_LONG).show();
                break;
            case "NOSUCHUSER":
                Toast.makeText(context, "Nie ma takiego użytkownika", Toast.LENGTH_LONG).show();
                break;

                // REGISTER
            case "LOGINDUPL":
                Toast.makeText(context, "Ten nick jest już zajęty", Toast.LENGTH_LONG).show();
                break;
            case "EMAILDUPL":
                Toast.makeText(context, "Ten E-mail jest już zajęty", Toast.LENGTH_LONG).show();
                break;

                // USER
            case "NOTREADY":
                Toast.makeText(context, "Uzupełnij swój profil, by móc korzystać z innych funkcji", Toast.LENGTH_LONG).show();
                break;

                //FRIENDS
            case "ALREADYRELATED":
                Toast.makeText(context, "Jesteście juz w relacji", Toast.LENGTH_LONG).show();
                break;
            case "NOTAFRIEND":
                Toast.makeText(context, "Ten użytkownik nie jest twoim znajomym", Toast.LENGTH_LONG).show();
                break;

                //EVENTS
            case "NOPERMISSION":
                Toast.makeText(context, "Tylko administrator lub moderator mogą edytować wydarzenie", Toast.LENGTH_LONG).show();
                break;
            case "NOSUCHEVENT":
                Toast.makeText(context, "To wydarzenie nie istnieje", Toast.LENGTH_LONG).show();
                break;
            case "NOTOWNER":
                Toast.makeText(context, "Nie jesteś administratorem", Toast.LENGTH_LONG).show();
                break;

                //IMAGES
            case "NOTPHOTO":
                Toast.makeText(context, "Adres url nie wskazuje na dostępne zdjęcie", Toast.LENGTH_LONG).show();
                break;
            case "ONLIMIT":
                Toast.makeText(context, "Osiąnięto limit zdjęć. Usuń zdjęcie, by dodać nowe", Toast.LENGTH_LONG).show();
                break;
            case "NOSUCHPHOTO":
                Toast.makeText(context, "Zdjęcie nie istnieje", Toast.LENGTH_LONG).show();
                break;

                //GENERAL
            case "BADBODY":
                Toast.makeText(context, "Zły JSON, zły!", Toast.LENGTH_LONG).show();
                break;
            case "NOTOKEN":
                Toast.makeText(context, "Nie wysłano tokenu", Toast.LENGTH_LONG).show();
                break;
            case "BADTOKEN":
                Toast.makeText(context, "Wysłano zły token", Toast.LENGTH_LONG).show();
                break;
            case "BADFORMAT":
                Toast.makeText(context, "Zły format", Toast.LENGTH_LONG).show();
                break;
            case "jwt malformed":
                Toast.makeText(context, "jwt malformed", Toast.LENGTH_LONG).show();
                break;
        }

        switch(message){
            case "Enter real email address":
                Toast.makeText(context, "Podano niewłaściwy format E-maila", Toast.LENGTH_LONG).show();
                break;
            case "No user with id provided, not inviting":
                Toast.makeText(context, "Brak podanego użytkownika", Toast.LENGTH_LONG).show();
                break;
            case "Login already taken":
                Toast.makeText(context, "Ten nick jest już zajęty", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
