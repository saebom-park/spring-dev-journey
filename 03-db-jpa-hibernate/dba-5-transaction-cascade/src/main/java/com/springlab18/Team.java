package com.springlab18;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    // 실무 기준 cascade 대신 명시적으로 save() 호출 하는 방식 선호
    // @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    @OneToMany(mappedBy = "team")
    List<Player> players = new ArrayList<>();

    // Constructor
    public Team() {}
    public Team(String name) { this.name = name; }

    // getter
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Player> getPlayers() { return players; }

    // 사용자 편의 메서드
    public void addPlayers(Player player) {
        players.add(player);
        player.setTeam(this);
    }
}