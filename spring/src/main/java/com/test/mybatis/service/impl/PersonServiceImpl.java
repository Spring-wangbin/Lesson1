package com.test.mybatis.service.impl;

import com.test.mybatis.dao.PersonDao;
import com.test.mybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Node;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void addPerson() {
        personDao.add();
        System.out.println("addPerson执行。。。");
    }

    @Override
    public void getAllPerson() {

    }
}
