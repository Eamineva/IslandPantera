package com.javarush.island.gainanshina.services.config;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.entities.animals.herbivore.*;
import com.javarush.island.gainanshina.entities.animals.predator.*;
import com.javarush.island.gainanshina.entities.plants.Plant;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс конфигурации для управления параметрами симуляции острова.
 * Содержит настраиваемые параметры: размеры острова, временные интервалы,
 * характеристики животных, вероятности событий и условия остановки.
 */
public class IslandConfig {
    // === ОСНОВНЫЕ ПАРАМЕТРЫ СИМУЛЯЦИИ ===

    /**
     * Ширина острова в клетках.
     */
    public static final int WIDTH = 100;
    /**
     * Высота острова в клетках.
     */
    public static final int HEIGHT = 20;

    /**
     * Интервал между тактами симуляции в миллисекундах.
     * Меньшее значение = более быстрая симуляция.
     */
    public static final int SIMULATION_TICK_DELAY_MS = 1000;
    /**
     * Интервал между ростом растений в миллисекундах.
     */
    public static final int PLANT_GROWTH_INTERVAL_MS = 3000;
    /**
     * Интервал вывода статистики в миллисекундах.
     */
    public static final int STATISTICS_INTERVAL_MS = 2000;

    /**
     * Максимальное количество растений на одной клетке.
     */
    public static final int MAX_PLANTS_PER_LOCATION = 200;

    /**
     * Карта вероятностей поедания между хищниками и жертвами.
     * Ключ: класс хищника, Значение: карта (класс жертвы → вероятность в процентах)
     *
     * <p>Пример: Wolf → (Rabbit → 60) означает, что волк съедает кролика
     * с вероятностью 60% при встрече на одной клетке.</p>
     */
    private static final Map<Class<? extends Animal>, Map<Class<?>, Integer>> EATING_PROBABILITIES =
            new HashMap<>();

    /**
     * Карта характеристик для каждого вида животных.
     * Ключ: класс животного, Значение: объект AnimalProperties с параметрами
     */
    private static final Map<Class<? extends Animal>, AnimalProperties> ANIMAL_PROPERTIES =
            new HashMap<>();

    static {
        initializeEatingProbabilities();
        initializeAnimalProperties();
    }
    /**
     * Карта вероятностей поедания для всех видов животных.
          */
    private static void initializeEatingProbabilities() {
        // Волк
        Map<Class<?>, Integer> wolfDiet = new HashMap<>();
        wolfDiet.put(Horse.class, 10);
        wolfDiet.put(Deer.class, 15);
        wolfDiet.put(Rabbit.class, 60);
        wolfDiet.put(Mouse.class, 80);
        wolfDiet.put(Goat.class, 60);
        wolfDiet.put(Sheep.class, 70);
        wolfDiet.put(Boar.class, 15);
        wolfDiet.put(Buffalo.class, 10);
        wolfDiet.put(Duck.class, 40);
        EATING_PROBABILITIES.put(Wolf.class, wolfDiet);

        // Удав
        Map<Class<?>, Integer> boaDiet = new HashMap<>();
        boaDiet.put(Fox.class, 15);
        boaDiet.put(Rabbit.class, 20);
        boaDiet.put(Mouse.class, 40);
        boaDiet.put(Duck.class, 10);
        EATING_PROBABILITIES.put(Boa.class, boaDiet);

        // Лиса
        Map<Class<?>, Integer> foxDiet = new HashMap<>();
        foxDiet.put(Rabbit.class, 70);
        foxDiet.put(Mouse.class, 90);
        foxDiet.put(Duck.class, 60);
        foxDiet.put(Caterpillar.class, 40);
        EATING_PROBABILITIES.put(Fox.class, foxDiet);

        // Медведь
        Map<Class<?>, Integer> bearDiet = new HashMap<>();
        bearDiet.put(Boa.class, 80);
        bearDiet.put(Horse.class, 40);
        bearDiet.put(Deer.class, 80);
        bearDiet.put(Rabbit.class, 80);
        bearDiet.put(Mouse.class, 90);
        bearDiet.put(Goat.class, 70);
        bearDiet.put(Sheep.class, 70);
        bearDiet.put(Boar.class, 50);
        bearDiet.put(Buffalo.class, 20);
        bearDiet.put(Duck.class, 10);
        EATING_PROBABILITIES.put(Bear.class, bearDiet);

        // Орел
        Map<Class<?>, Integer> eagleDiet = new HashMap<>();
        eagleDiet.put(Fox.class, 10);
        eagleDiet.put(Rabbit.class, 90);
        eagleDiet.put(Mouse.class, 90);
        eagleDiet.put(Duck.class, 80);
        EATING_PROBABILITIES.put(Eagle.class, eagleDiet);

        // Травоядные едят растения с вероятностью 100%
        Class<? extends Animal>[] herbivores = new Class[]{
                Horse.class, Deer.class, Rabbit.class, Mouse.class,
                Goat.class, Sheep.class, Boar.class, Buffalo.class,
                Duck.class, Caterpillar.class
        };

        for (Class<? extends Animal> herbivore : herbivores) {
            Map<Class<?>, Integer> plantDiet = new HashMap<>();
            plantDiet.put(Plant.class, 100);
            EATING_PROBABILITIES.put(herbivore, plantDiet);

            // Для утки, мыши и кабана добавляем возможность есть гусениц
            if (herbivore == Duck.class || herbivore == Mouse.class || herbivore == Boar.class) {
                plantDiet.put(Caterpillar.class, 90);
            }
        }
    }
    /**
     * Инициализирует карту характеристик для всех видов животных.
     * Заполняет ANIMAL_PROPERTIES параметрами каждого вида:
     * - Вес животного
     * - Максимальное количество на клетке
     * - Скорость перемещения
     * - Потребность в пище
     * - Вероятность размножения
     */
    private static void initializeAnimalProperties() {
        ANIMAL_PROPERTIES.put(Wolf.class, new AnimalProperties(50, 30, 3, 8, 0.5));
        ANIMAL_PROPERTIES.put(Boa.class, new AnimalProperties(15, 30, 1, 3, 0.5));
        ANIMAL_PROPERTIES.put(Fox.class, new AnimalProperties(8, 30, 2, 2, 0.5));
        ANIMAL_PROPERTIES.put(Bear.class, new AnimalProperties(500, 5, 2, 80, 0.5));
        ANIMAL_PROPERTIES.put(Eagle.class, new AnimalProperties(6, 20, 3, 1, 0.5));

        ANIMAL_PROPERTIES.put(Horse.class, new AnimalProperties(400, 20, 4, 60, 0.5));
        ANIMAL_PROPERTIES.put(Deer.class, new AnimalProperties(300, 20, 4, 50, 0.5));
        ANIMAL_PROPERTIES.put(Rabbit.class, new AnimalProperties(2, 150, 2, 0.45, 0.5));
        ANIMAL_PROPERTIES.put(Mouse.class, new AnimalProperties(0.05, 500, 1, 0.01, 0.5));
        ANIMAL_PROPERTIES.put(Goat.class, new AnimalProperties(60, 140, 3, 10, 0.5));
        ANIMAL_PROPERTIES.put(Sheep.class, new AnimalProperties(70, 140, 3, 15, 0.5));
        ANIMAL_PROPERTIES.put(Boar.class, new AnimalProperties(400, 50, 2, 50, 0.5));
        ANIMAL_PROPERTIES.put(Buffalo.class, new AnimalProperties(700, 10, 3, 100, 0.5));
        ANIMAL_PROPERTIES.put(Duck.class, new AnimalProperties(1, 200, 4, 0.15, 0.5));
        ANIMAL_PROPERTIES.put(Caterpillar.class, new AnimalProperties(0.01, 1000, 0, 0, 0.5));
    }

    /**
     * Возвращает вероятность поедания для заданной пары хищник-жертва.
     */
    public static int getEatingProbability(Class<? extends Animal> predator, Class<?> prey) {
        Map<Class<?>, Integer> diet = EATING_PROBABILITIES.get(predator);
        if (diet != null && diet.containsKey(prey)) {
            return diet.get(prey);
        }
        return 0;
    }

    public static AnimalProperties getProperties(Class<? extends Animal> animalClass) {
        return ANIMAL_PROPERTIES.get(animalClass);
    }

    public static class AnimalProperties {
        public final double weight;
        public final int maxPerLocation;
        public final int maxSpeed;
        public final double foodRequired;
        public final double reproductionProbability;

        public AnimalProperties(double weight, int maxPerLocation, int maxSpeed,
                                double foodRequired, double reproductionProbability) {
            this.weight = weight;
            this.maxPerLocation = maxPerLocation;
            this.maxSpeed = maxSpeed;
            this.foodRequired = foodRequired;
            this.reproductionProbability = reproductionProbability;
        }
    }
}