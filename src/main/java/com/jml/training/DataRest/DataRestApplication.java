package com.jml.training.DataRest;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SpringBootApplication
public class DataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataRestApplication.class, args);
	}
	
	@Bean
	CommandLineRunner executeRun(PlayerRepository playerRepository) {
		return args -> {
			Arrays.asList(new Player(1L, "name", "team"),
					new Player(2L, "name1", "team1"),
					new Player(3L, "name2", "team2")).forEach(player -> playerRepository.save(player));
			
			playerRepository.findAll().forEach(System.out::println);
			System.out.println(playerRepository.findByName("name1"));
		};
	}
}

@RepositoryRestResource(collectionResourceRel = "player", path = "players")
interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
	Player findByName(@Param("name") String name);
}


@Entity
class Player {
	
	@Id @GeneratedValue
	private Long id;
	private String name;
	private String team;
	
	public Player() {
	}
	
	public Player(Long id, String name, String team) {
		this.id = id;
		this.name = name;
		this.team = team;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "Players [id=" + id + ", name=" + name + ", team=" + team + "]";
	}
	
}
