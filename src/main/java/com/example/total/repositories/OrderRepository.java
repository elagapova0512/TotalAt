package com.example.total.repositories;

import com.example.total.models.Order;
import com.example.total.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByPerson(Person person);// получаем все заказы по объекту Персон

    Optional<Order> findOrderById(int id);
    @Query(value = "select * from order_site where number = ?1", nativeQuery = true)
    Optional<Order> findByNumber(String number);
//    @Query(value="select ")
//    List<Order> uniteOrder();

    List<Order> findByNumberEndingWithIgnoreCase(String endingWith);


}


