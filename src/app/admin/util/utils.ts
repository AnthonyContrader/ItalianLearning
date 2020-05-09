import { Injectable } from '@angular/core';
import { GamePlaylistService } from 'src/service/gameplaylist.service';
import { FindAWordService } from 'src/service/findaword.service';
import { FindMistakeService } from 'src/service/findmistake.service';
import { GuessPictureService } from 'src/service/guesspicture.service';
import { HangmanService } from 'src/service/hangman.service';
import { OrganizeSentenceService } from 'src/service/organizesentence.service';
import { QuizService } from 'src/service/quiz.service';

@Injectable({
    providedIn: 'root',
  })


export class Utils {
    
    public static getAllGames(playlistId: number){

        
        
        let list = new Map<String, any>();
        let gameList = [];
        
        
    }
}