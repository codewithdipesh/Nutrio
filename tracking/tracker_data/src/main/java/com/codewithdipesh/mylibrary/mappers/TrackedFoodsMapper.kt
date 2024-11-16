package com.codewithdipesh.mylibrary.mappers

import com.codewithdipesh.mylibrary.local.entity.TrackedFoodEntity
import com.codewithdipesh.tracker_domain.model.MealType
import com.codewithdipesh.tracker_domain.model.Nutrients
import com.codewithdipesh.tracker_domain.model.TrackedFood
import com.codewithdipesh.tracker_domain.model.Unit
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood{
   var nutrients : Map<Unit,Nutrients> = mapOf(
       Unit.TableSpoon to Nutrients(TableSpoonCalories,TableSpoonCarbs,TableSpoonProtein,TableSpoonFat,TableSpoonFiber),
       Unit.TeaSpoon to Nutrients(TeaSpoonCalories,TeaSpoonCarbs,TeaSpoonProtein,TeaSpoonFat,TeaSpoonFiber),
       Unit.Ounce to Nutrients(OunceCalories,OunceCarbs,OunceProtein,OunceFat,OunceFiber),
       Unit.Whole to Nutrients(WholeCalories,WholeCarbs,WholeProtein,WholeFat,WholeFiber),
       Unit.Gm100 to Nutrients(Gm100Calories,Gm100Carbs,Gm100Protein,Gm100Fat,Gm100Fiber),
       Unit.Cup to Nutrients(CupCalories,CupCarbs,CupProtein,CupFat,CupFiber)
   )
   return TrackedFood(
       name = name,
       _carbs=carbs,
       _protein=protein,
       _fat = fat,
       unit = Unit.fromString(unit),
       mealType = MealType.fromString(type),
       amount = amount,
       _fiber = fibers,
       date = LocalDate.of(year, month, dayOfMonth),
       calories = calories,
       nutrients = nutrients,
       id = id
   )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity{
    return TrackedFoodEntity(
        name = name,
        carbs=carbs,
        protein=protein,
        fat = fat,
        fibers = fiber,
        unit = unit.name,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        TableSpoonCalories = nutrients[Unit.TableSpoon]?.calories ?: 0.0 ,
        TableSpoonCarbs = nutrients[Unit.TableSpoon]?.carbs ?: 0.0,
        TableSpoonProtein = nutrients[Unit.TableSpoon]?.protein ?: 0.0,
        TableSpoonFat = nutrients[Unit.TableSpoon]?.fat ?: 0.0,
        TableSpoonFiber = nutrients[Unit.TableSpoon]?.fiber ?: 0.0,
        TeaSpoonCalories = nutrients[Unit.TeaSpoon]?.calories ?: 0.0 ,
        TeaSpoonCarbs = nutrients[Unit.TeaSpoon]?.carbs ?: 0.0,
        TeaSpoonProtein = nutrients[Unit.TeaSpoon]?.protein ?: 0.0,
        TeaSpoonFat = nutrients[Unit.TeaSpoon]?.fat ?: 0.0,
        TeaSpoonFiber = nutrients[Unit.TeaSpoon]?.fiber ?: 0.0,
        OunceCalories = nutrients[Unit.Ounce]?.calories ?: 0.0 ,
        OunceCarbs = nutrients[Unit.Ounce]?.carbs ?: 0.0,
        OunceProtein = nutrients[Unit.Ounce]?.protein ?: 0.0,
        OunceFat = nutrients[Unit.Ounce]?.fat ?: 0.0,
        OunceFiber = nutrients[Unit.Ounce]?.fiber ?: 0.0,
        WholeCalories = nutrients[Unit.Whole]?.calories ?: 0.0 ,
        WholeCarbs = nutrients[Unit.Whole]?.carbs ?: 0.0,
        WholeProtein = nutrients[Unit.Whole]?.protein ?: 0.0,
        WholeFat = nutrients[Unit.Whole]?.fat ?: 0.0,
        WholeFiber = nutrients[Unit.Whole]?.fiber ?: 0.0,
        Gm100Calories = nutrients[Unit.Gm100]?.calories ?: 0.0 ,
        Gm100Carbs = nutrients[Unit.Gm100]?.carbs ?: 0.0,
        Gm100Protein = nutrients[Unit.Gm100]?.protein ?: 0.0,
        Gm100Fat = nutrients[Unit.Gm100]?.fat ?: 0.0,
        Gm100Fiber = nutrients[Unit.Gm100]?.fiber ?: 0.0,
        CupCalories = nutrients[Unit.Cup]?.calories ?: 0.0 ,
        CupCarbs = nutrients[Unit.Cup]?.carbs ?: 0.0,
        CupProtein = nutrients[Unit.Cup]?.protein ?: 0.0,
        CupFat = nutrients[Unit.Cup]?.fat ?: 0.0,
        CupFiber = nutrients[Unit.Cup]?.fiber ?: 0.0,
        id = id
    )
}