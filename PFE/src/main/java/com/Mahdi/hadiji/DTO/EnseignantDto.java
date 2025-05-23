package com.Mahdi.hadiji.DTO;

public class EnseignantDto {
    private Long id;
    private int matriculeEns;
    private String nom;
    private String prenom;
    private String email;

    public EnseignantDto(Long id, int matriculeEns, String nom, String prenom, String email) {
        this.id = id;
        this.matriculeEns = matriculeEns;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    // getters/setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMatriculeEns() {
		return matriculeEns;
	}

	public void setMatriculeEns(int matriculeEns) {
		this.matriculeEns = matriculeEns;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
