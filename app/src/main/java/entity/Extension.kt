package entity

import case.ListPetSummariesCase.PetSummary

fun Pet.toPetSummary() = PetSummary(id, name, imageUrl)
