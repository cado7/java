package com.cado.swingy.controller;

import com.cado.swingy.exception.HeroValidationException;
import com.cado.swingy.model.Game;
import com.cado.swingy.model.character.Hero;
import com.cado.swingy.util.DataBase;
import com.cado.swingy.view.select.SelectHeroView;

import java.util.ArrayList;


public class SelectHeroController {

    private SelectHeroView view;
    private Game game;

    public SelectHeroController(SelectHeroView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void onListElementSelected(int idx) {
        Hero hero = DataBase.selectHeroById(idx + 1);
        view.updateInfo(hero.toString());
    }

    public String[] getListData() {
        ArrayList<String> list = DataBase.selectAll();
        String[] listArr = new String[list.size()];
        listArr = list.toArray(listArr);
        return listArr;
    }

    public void onSelectButtonPressed(int idx) {
        Hero hero;
        try {
            hero = DataBase.selectHeroById(idx + 1);
            hero.validateHero();
        } catch (HeroValidationException e) {
            view.showErrorMessage(e.getMessage());
            return;
        }

        game.initGame(hero);
        view.openGame();
    }

    public void onCreateButtonPressed() {
        view.openCreateHero();
    }
}
