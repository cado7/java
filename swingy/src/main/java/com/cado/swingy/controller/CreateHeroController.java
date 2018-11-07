package com.cado.swingy.controller;

import com.cado.swingy.exception.HeroValidationException;
import com.cado.swingy.model.Game;
import com.cado.swingy.model.character.Hero;
import com.cado.swingy.model.character.HeroFactory;
import com.cado.swingy.util.DataBase;
import com.cado.swingy.view.create.CreateHeroView;

public class CreateHeroController {

    private CreateHeroView view;
    private Game game;

    public CreateHeroController(CreateHeroView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void onCreateButtonPressed(String name, String heroClass) {
        Hero hero;
        try {
            hero = HeroFactory.newHero(name, heroClass);
            hero.validateHero();
        } catch (IllegalArgumentException | HeroValidationException e) {
            view.showErrorMessage(e.getMessage());
            view.getUserInput();
            return;
        }

        int id = DataBase.insert(hero.getName(), hero.getHeroClass(), hero.getLevel(), hero.getExperience(), hero.getAttack(), hero.getDefense(), hero.getHitPoints());
        hero.setId(id);
        game.initGame(hero);
        view.openGame();
    }
}
