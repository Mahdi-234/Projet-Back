package com.Mahdi.hadiji.DTO;

import lombok.Data;

@Data
public class VoeuxCreateDTO {
    private Long enseignantId;
    private Long elementConstitutifId;
    private int nbGroupeCours;
    private int nbGroupeTd;
    private int nbGroupeTp;
    private int nbGroupeAutre;
}
