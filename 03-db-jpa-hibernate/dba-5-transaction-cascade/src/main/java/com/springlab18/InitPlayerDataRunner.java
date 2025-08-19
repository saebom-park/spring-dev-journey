package com.springlab18;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitPlayerDataRunner implements CommandLineRunner {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    // Constructor
    public InitPlayerDataRunner(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Team t1 = new Team("드림FC");
        Player p1 = new Player("봄이", "스트라이커");
        Player p2 = new Player("온이", "골키퍼");

        t1.addPlayers(p1);
        t1.addPlayers(p2);
        teamRepository.save(t1);
        playerRepository.save(p1);
        playerRepository.save(p2);

        teamRepository.findAll().forEach(team -> {
            System.out.println(team.getName() + "팀의 선수 명단");
            team.getPlayers().forEach(p -> System.out.println("- " + p.getName() + " (" + p.getPosition() + ")"));
        });
    }
}