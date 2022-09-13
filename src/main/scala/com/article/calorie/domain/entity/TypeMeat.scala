package com.article.calorie.domain.entity

sealed trait TypeMeat
case object Breakfast extends TypeMeat
case object Lance     extends TypeMeat
case object Dinner    extends TypeMeat
