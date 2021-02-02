package com.thoughtworks.security.scpapi.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddUseCaseRequest {

    List<Integer> useCaseIds;
}
