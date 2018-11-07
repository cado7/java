package com.cado.swingy.model.character;

import com.cado.swingy.model.artifact.Artifact;

/**
 * Created by chvs on 18.06.2018.
 */
public class Villain extends Character {

    private Artifact artifact;

    public Villain(String name, int attack, int defense, int hitPoints, Artifact artifact) {
        super(name, attack, defense, hitPoints);
        this.artifact = artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}
