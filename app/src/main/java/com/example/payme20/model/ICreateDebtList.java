/* The responsibility of this interface is to enable use of poly-morphism of
classes that implement this interface.
*/
package com.example.payme20.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Map;

/**
 * The responsibility of this interface is to enable use of polymorphism of different ways to
 * create a debt list for an event
 */
@JsonDeserialize(as = SplitCreateDebtList.class)
public interface ICreateDebtList {
    List<Debt> createDebtList(Map<Member, Integer> map, Member Payer);

    String getName();
}

