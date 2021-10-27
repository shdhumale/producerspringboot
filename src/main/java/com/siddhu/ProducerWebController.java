package com.siddhu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.tutorial.protos.AddressBook;
import com.example.tutorial.protos.Person;
import com.example.tutorial.protos.Person.PhoneNumber;
import com.example.tutorial.protos.Person.PhoneType;
import com.example.tutorial.protos.PersonModel;

@RestController
public class ProducerWebController {

	@Autowired
	RestTemplate restTemplate;


	// GET Method - To Select the data.
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public String getPerson() {
		return getData().toString();

	}

	// POST Method :- To insert the data.
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public String createPerson(@RequestBody PersonModel personModel) {
		//	      HttpHeaders headers = new HttpHeaders();
		//	      MediaType mediaType = new MediaType("application", "x-protobuf");
		//	      List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		//	      acceptableMediaTypes.add(mediaType);
		//	      headers.setAccept(acceptableMediaTypes);
		//	      HttpEntity<PersonModel> entity = new HttpEntity<PersonModel>(personModel,headers);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<PersonModel> entity = new HttpEntity<PersonModel>(personModel,headers);
		Person.Builder builder = getData();
		builder.setId(entity.getBody().getId());
		builder.setName(entity.getBody().getName());
		return builder.toString();

	}

	// PUT Method :- To update the data.
	@RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
	public String updatePerson(@PathVariable("id") String id, @RequestBody PersonModel personModel) {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "x-protobuf");
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(mediaType);
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<PersonModel> entity = new HttpEntity<PersonModel>(personModel,headers);

		Person.Builder builder = getData();
		builder.setName(entity.getBody().getName());
		return builder.toString();


	}


	// DELETE Method :- To delete the data.
	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
	public String deletePerson(@PathVariable("id") String id) {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "x-protobuf");
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(mediaType);
		headers.setAccept(acceptableMediaTypes);

		Person.Builder builder = getData();
		builder.clearId();
		return builder.toString();


	}

	//Data preparation Start[

	private Person.Builder getData()
	{
		Person.Builder builder = Person.newBuilder();
		builder.setName("siddhu");
		builder.setId(126);
		builder.setEmail("shdhumale@gmail.com");

		System.out.println("builder.toString():-"+builder.toString());

		PhoneNumber.Builder PhoneNumberBuilder = PhoneNumber.newBuilder();
		PhoneNumberBuilder.setNumber("1111111");
		PhoneNumberBuilder.setType(PhoneType.HOME);
		//builder.setPhones(0, PhoneNumberBuilder);


		PhoneNumber.Builder PhoneNumberBuilder1 = PhoneNumber.newBuilder();
		PhoneNumberBuilder1.setNumber("222222222");
		PhoneNumberBuilder1.setType(PhoneType.MOBILE);
		//builder.setPhones(1, PhoneNumberBuilder1);


		builder.addPhones(PhoneNumberBuilder).addPhones(PhoneNumberBuilder1);


		System.out.println("builder after adding PhoneNumberBuilder1.toString():-"+builder.toString());


		AddressBook.Builder AddressBookBuilder = AddressBook.newBuilder();
		//AddressBookBuilder.setPeople(1, builder);
		AddressBookBuilder.addPeople(builder);



		System.out.println("AddressBookBuilder.toString():-"+AddressBookBuilder.toString());
		return builder;
	}

	//Data preparation End]

}
