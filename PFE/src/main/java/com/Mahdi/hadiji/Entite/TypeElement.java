package com.Mahdi.hadiji.Entite;

public enum TypeElement {
    normal("normal"),
    integrercourstd("intégré cours + TD"),
    integrercourstp("intégré cours + TP");

    private final String label;

    TypeElement(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

