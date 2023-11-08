package com.guilhermekumagai.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.guilhermekumagai.workshopmongo.domain.Post;
import com.guilhermekumagai.workshopmongo.domain.User;
import com.guilhermekumagai.workshopmongo.dto.AuthorDTO;
import com.guilhermekumagai.workshopmongo.dto.CommentDTO;
import com.guilhermekumagai.workshopmongo.repository.PostRepository;
import com.guilhermekumagai.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User paulo = new User(null, "Paulo Silva", "psilva@gmail.com");
		User maria = new User(null, "Maria Rodrigues", "marirod@gmail.com");
		User juliana = new User(null, "Juliana dos Santos", "julis@gmail.com");
		
		userRepository.saveAll(Arrays.asList(paulo, maria, juliana));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Vou viajar", "Farei muitas compras la!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Boa Noite", "Comi muito na janta!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem!", sdf.parse("21/03/2018"), new AuthorDTO(paulo));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(juliana));
		CommentDTO c3 = new CommentDTO("O que voce comeu?", sdf.parse("25/03/2018"), new AuthorDTO(paulo));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		postRepository.saveAll(Arrays.asList(post1, post2));
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
