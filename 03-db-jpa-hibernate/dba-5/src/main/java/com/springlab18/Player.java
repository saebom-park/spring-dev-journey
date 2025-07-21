package com.springlab18;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Column(name = "player_name")
    private String name;

    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    // Constructor
    public Player() {}
    public Player(String name, String position) {
        this.name = name;
        this.position = position;
    }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }

    // setter
    public void setTeam(Team team) { this.team = team; }
}