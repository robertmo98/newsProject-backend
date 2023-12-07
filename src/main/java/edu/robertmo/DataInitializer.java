package edu.robertmo;

import edu.robertmo.newsproject.dto.request.ArticleRequestDto;
import edu.robertmo.newsproject.entity.Article;
import edu.robertmo.newsproject.entity.Role;
import edu.robertmo.newsproject.entity.User;
import edu.robertmo.newsproject.repository.ArticleRepository;
import edu.robertmo.newsproject.repository.RoleRepository;
import edu.robertmo.newsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public void run(String... args) throws Exception {
//          initializeRoles();
//          initializeUsers();
//        initializeArticles();
    }


    private void initializeRoles() {
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            roleRepository.save(new Role(1L, "ROLE_ADMIN"));
        }

        if (!roleRepository.existsByName("ROLE_USER")) {
            roleRepository.save(new Role(2L, "ROLE_USER"));
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0 || !userRepository.existsUserByUsernameIgnoreCase("admin")) {
            User adminUser = User.builder()
                    .username("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("password"))
                    .roles(Set.of(roleRepository.findByName("ROLE_ADMIN")))
                    .build();

            userRepository.save(adminUser);
        }
    }


    private void initializeArticles() {
        if (articleRepository.count() != 0) {
            User user = userRepository.findByUsername("admin");

            ArticleRequestDto dto1 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("The Enchanting Depths: Unveiling the Wonders of Ocean Life")
                    .content("Marine biology, a captivating scientific discipline, delves into the vast and diverse life inhabiting the world's oceans. From microscopic plankton to colossal whales, marine biologists explore a wide array of organisms, each contributing to the intricate ecosystem beneath the ocean's surface. This field plays a crucial role in understanding and preserving the delicate balance that sustains life in the oceans.\n" +
                            "\n" +
                            "Recent research in marine biology has yielded groundbreaking discoveries, unveiling new species of deep-sea creatures and shedding light on the mysteries of the ocean depths. These findings not only expand our scientific knowledge but also underscore the urgency of conservation efforts to protect these fragile ecosystems.\n" +
                            "\n" +
                            "One aspect that continues to captivate researchers is the adaptability of marine organisms to their environment. The ocean, with its varying conditions and challenges, requires marine life to develop unique survival strategies. Whether it's the resilience of coral reefs or the mysterious behaviors of deep-sea creatures, marine biology provides a window into the remarkable world beneath the waves.\n" +
                            "\n" +
                            "Exploring Ocean Biodiversity remains a primary focus, as scientists strive to document and understand the myriad species that contribute to the richness of marine life. The interconnectedness of these species, from the smallest organisms to the largest predators, highlights the importance of a holistic approach to marine biology.\n" +
                            "\n" +
                            "The technological advancements in underwater exploration have played a pivotal role in these discoveries. Remote-operated vehicles (ROVs) and autonomous underwater vehicles (AUVs) equipped with high-definition cameras enable scientists to reach depths previously inaccessible. These tools provide a glimpse into the hidden corners of the ocean, revealing new species and behaviors that continue to astound the scientific community.\n" +
                            "\n" +
                            "Moreover, marine biology extends beyond the scientific realm. It beckons humanity to recognize the intrinsic value of preserving our oceans. The delicate balance maintained by marine ecosystems not only ensures the survival of countless species but also directly impacts global climate regulation.\n" +
                            "\n" +
                            "In conclusion, the fascinating world of marine biology encompasses not only the scientific exploration of ocean life but also the imperative to protect and preserve these ecosystems. As technology advances and our understanding deepens, the mysteries of the ocean continue to unfold, emphasizing the need for responsible stewardship to safeguard the wonders that lie beneath the surface.\n" +
                            "\n")
                    .secondaryTitle("Exploring Ocean Biodiversity")
                    .mainImg("https://images.pexels.com/photos/2832772/pexels-photo-2832772.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Jelly Fish With Reflection Of Blue Light\n")
                    .mainImgCredit("Pexels, Magda Ehlers")
                    .secondImg("URL_TO_BIOLOGY_IMAGE_2")
                    .secondImgDescription("An Expedition Boat in the Sea\n")
                    .secondImgCredit("pexels, Arthur FlyingPenguin Guillemot")
                    .build();

            Article entity1 = modelMapper.map(dto1, Article.class);
            entity1.setUser(user);
            entity1.setDate(LocalDate.now());
            articleRepository.save(entity1);


            ArticleRequestDto dto2 = ArticleRequestDto.builder()
                    .category("Chemistry")
                    .title("The Marvels of Organic Chemistry")
                    .content("Organic chemistry, a captivating realm within the vast landscape of chemical sciences, focuses on unraveling the mysteries of carbon-containing compounds. These compounds, omnipresent in our lives, form the backbone of diverse substances, from life-saving drugs to innovative materials shaping the modern world. In the intricate dance of atoms, organic chemistry plays a pivotal role, and its impact reverberates across multiple facets of our daily existence.\n" +
                            "\n" +
                            "At the core of organic chemistry lies the exploration of the structure, properties, and reactions of organic compounds. These compounds, characterized by the presence of carbon atoms, exhibit a remarkable diversity that spans the simple hydrocarbons to the intricately structured biomolecules essential for life. Researchers in this field navigate the complexities of molecular architecture, seeking to understand and manipulate the building blocks of nature.\n" +
                            "\n" +
                            "The synthesis of life-saving drugs stands as a testament to the profound contributions of organic chemistry to the field of medicine. From antibiotics that combat infectious diseases to sophisticated treatments for chronic conditions, the organic chemist's laboratory serves as a crucible for innovation. Every breakthrough in drug development is a result of meticulous experimentation, where scientists design and synthesize organic molecules with targeted properties, aiming to enhance therapeutic efficacy and minimize side effects.\n" +
                            "\n" +
                            "Beyond the realms of medicine, organic chemistry catalyzes advancements in materials science, driving the creation of innovative substances that redefine possibilities. In laboratories around the world, researchers push the boundaries of organic synthesis, developing new methods to construct molecules with unprecedented precision. These synthetic marvels find applications in fields as diverse as electronics, materials engineering, and environmental science, ushering in an era of sustainable technologies.\n" +
                            "\n" +
                            "Recent breakthroughs in organic chemistry have witnessed scientists engineer molecules with properties previously deemed unattainable. The synthesis of organic compounds with unique electronic, optical, or mechanical characteristics opens new avenues for scientific exploration and technological innovation. In the quest for sustainability, organic chemists are at the forefront, designing environmentally friendly materials that mitigate the impact of human activities on the planet.\n" +
                            "\n" +
                            "Visualizing the world of organic chemistry brings to mind the captivating imagery of a laboratory flask, where chemical reactions unfold in a ballet of molecular transformations. The main image of a chemical reaction in a laboratory flask encapsulates the essence of organic synthesis – a delicate interplay of reactants yielding transformative products. This visual narrative reinforces the notion that organic chemistry is an art as much as it is a science, where creativity and precision converge.\n" +
                            "\n" +
                            "Accompanying this visual journey is a glimpse into the realm of scientists immersed in the study of organic compounds. The second image captures a scientist engrossed in the meticulous work with organic compounds, exemplifying the dedication and expertise required in this scientific discipline. The laboratory environment, with its array of glassware and instruments, becomes the backdrop for discoveries that shape the future of chemistry.\n" +
                            "\n" +
                            "In conclusion, the marvels of organic chemistry unfold in a narrative of discovery, innovation, and societal impact. From life-saving drugs to sustainable materials, this field continues to shape the trajectory of scientific progress. The images accompanying this exploration serve not only as visual aids but also as windows into the dynamic and fascinating world where scientists unravel the secrets of organic compounds, propelling us into a future where the possibilities of molecular design are boundless.")
                    .secondaryTitle("Innovations in Organic Synthesis")
                    .mainImg("https://images.pexels.com/photos/248152/pexels-photo-248152.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Two Clear Glass Jars Beside Several Flasks")
                    .mainImgCredit("Pixabay")
                    .secondImg("https://images.pexels.com/photos/2280549/pexels-photo-2280549.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Laboratory Test Tubes")
                    .secondImgCredit("Pexels, Chokniti Khongchum")
                    .build();

            Article entity2 = modelMapper.map(dto2, Article.class);
            entity2.setUser(user);
            entity2.setDate(LocalDate.now());
            articleRepository.save(entity2);


            ArticleRequestDto dto3 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("Unlocking the Mysteries of Quantum Physics")
                    .content("Quantum physics, a captivating frontier in the vast expanse of physics, unravels the enigmatic behavior of particles at the quantum level. This branch of physics, marked by its strange and counterintuitive phenomena, has intrigued scientists for decades. The exploration of quantum mechanics delves into concepts like superposition and entanglement, where particles exist in multiple states simultaneously and become interconnected across vast distances. This article embarks on a journey into the profound realm of quantum physics, shedding light on its fundamentals and exploring the potential it holds to revolutionize technology.\n" +
                            "\n" +
                            "At the heart of quantum physics is the peculiar behavior of quantum particles existing in a superposition state. The main image vividly illustrates this fascinating phenomenon, portraying particles in a simultaneous dance of possibilities. This depiction captures the essence of the quantum realm, where particles defy classical intuition and embrace a spectrum of states until observed. The visual narrative serves as a gateway to understanding the inherent complexity and beauty woven into the fabric of quantum mechanics.\n" +
                            "\n" +
                            "Complementing this visual exploration is an image that takes us into the laboratory, where quantum physicists conduct experiments to unravel the mysteries of the quantum world. The second image encapsulates the essence of scientific inquiry, with a quantum physicist engaged in the meticulous process of probing quantum phenomena. The laboratory setting, adorned with advanced instruments and technology, becomes the stage for groundbreaking experiments that push the boundaries of our understanding.\n" +
                            "\n" +
                            "The content of this article extends beyond theoretical concepts, delving into the practical applications of quantum mechanics that have emerged from recent experiments. These applications are poised to transform technology, with quantum computing standing out as a revolutionary frontier. The article navigates through the advancements that have paved the way for quantum computing, a paradigm-shifting approach to information processing that leverages the unique properties of quantum bits, or qubits.\n" +
                            "\n" +
                            "Secure communication, another practical application of quantum physics, is explored as scientists harness the principles of quantum entanglement to enable ultra-secure transmission of information. The potential impact on cryptography and communication technologies underscores the far-reaching consequences of unlocking the mysteries of quantum physics.\n" +
                            "\n" +
                            "In conclusion, this exploration into the mysteries of quantum physics serves as a gateway to understanding a realm where classical laws cease to hold sway. The article invites readers to embark on a journey into the quantum realm, where particles exhibit behaviors that challenge our fundamental understanding of the universe. Through captivating imagery and insightful content, the article illuminates the path paved by scientists as they unlock the secrets of quantum mechanics, opening doors to technological advancements that could reshape the future.")
                    .secondaryTitle("Journey into the Quantum Realm")
                    .mainImg("https://images.pexels.com/photos/714699/pexels-photo-714699.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Person Holding a Chalk in Front of the Chalk Board")
                    .mainImgCredit("pexels, JESHOOTS.com")
                    .secondImg("https://images.pexels.com/photos/6256258/pexels-photo-6256258.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Hand Writing Mathematical Formulas on a Blackboard")
                    .secondImgCredit("pexels, Karolina Grabowska")
                    .build();

            Article entity3 = modelMapper.map(dto3, Article.class);
            entity3.setUser(user);
            entity3.setDate(LocalDate.now());
            articleRepository.save(entity3);


            ArticleRequestDto dto4 = ArticleRequestDto.builder()
                    .category("Space")
                    .title("The Wonders of Our Solar System")
                    .content("The vast expanse of our solar system unfolds as a cosmic neighborhood, teeming with wonders that captivate the imagination. Each celestial body, from the blazing sun at the system's heart to the frigid realms where comets traverse, weaves a unique narrative of formation and evolution. This article embarks on a celestial odyssey, guided by recent space missions that have unveiled unprecedented insights into the mysteries of our solar system, providing breathtaking images and unraveling clues about the origins of our cosmic home.\n" +
                            "\n" +
                            "At the core of our solar system lies the radiant sun, a celestial giant that bathes the planets in its life-sustaining light. From the sun's fiery embrace, the article ventures outward to explore the diverse planets and moons that orbit in harmonious dance. Each celestial body becomes a storyteller, recounting tales of its birth, the sculpting forces of gravity, and the eons-long journey through the cosmic theater.\n" +
                            "\n" +
                            "Saturn, adorned with its iconic rings, takes center stage in the first image, a mesmerizing spectacle of nature's artistry. The rings, composed of icy particles and rocky debris, shine brightly against the backdrop of space. The image captures the allure of Saturn's majestic rings, inviting readers to marvel at the intricacies of planetary dynamics and the forces that shape these cosmic jewels.\n" +
                            "\n" +
                            "The exploration continues with a second image, offering a glimpse into the realm of space missions capturing images of distant planets. A spacecraft, equipped with advanced imaging technology, becomes the eyes through which humanity witnesses the awe-inspiring landscapes of alien worlds. The visual narrative unfolds as the spacecraft navigates the cosmos, transmitting images that reveal the unique features and mysteries concealed within the atmospheres and terrains of distant planets.\n" +
                            "\n" +
                            "Recent space missions have been instrumental in providing humanity with a front-row seat to the cosmic drama, showcasing the breathtaking beauty and complexity of our solar system. These missions, equipped with state-of-the-art instruments, capture images that transcend the boundaries of our imagination, sparking a sense of wonder and inspiring curiosity about the vastness of the universe.\n" +
                            "\n" +
                            "In conclusion, \"The Wonders of Our Solar System\" is an exploration into the celestial tapestry that surrounds us. Through captivating imagery and insightful content, the article invites readers to embark on a journey of cosmic discovery, where each planet and moon becomes a chapter in the ongoing saga of our solar system. As space missions continue to push the boundaries of exploration, the mysteries of our cosmic home are gradually unveiled, offering a glimpse into the grandeur and complexity of the universe.\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n")
                    .secondaryTitle("Exploring Planets and Moons")
                    .mainImg("https://images.pexels.com/photos/1983032/pexels-photo-1983032.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Phases Of The Moon")
                    .mainImgCredit("pexels, Alex Andrews")
                    .secondImg("https://images.pexels.com/photos/355935/pexels-photo-355935.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Earth Illustration")
                    .secondImgCredit("Pixabay")
                    .build();

            Article entity4 = modelMapper.map(dto4, Article.class);
            entity4.setUser(user);
            entity4.setDate(LocalDate.now());
            articleRepository.save(entity4);


            ArticleRequestDto dto5 = ArticleRequestDto.builder()
                    .category("Space")
                    .title("Celestial Odyssey: Navigating the Wonders of Our Solar System")
                    .content("Embark on a cosmic voyage as we unravel the wonders of our solar system, a vast expanse where celestial bodies weave tales of formation and evolution. From the radiant sun, the life-giving heart of our cosmic neighborhood, to the icy outskirts where comets roam, each planet and moon reveals a unique narrative etched by the forces of the cosmos. Recent space missions have opened a window into the mysteries of our solar system, providing unprecedented insights through breathtaking images and uncovering clues about the origins of our cosmic home.\n" +
                            "\n" +
                            "Saturn, adorned with its iconic rings, takes center stage in the first image—a dazzling display of nature's artistry. The rings, composed of icy particles and rocky debris, shine brightly against the backdrop of space. This celestial ballet, captured in meticulous detail, invites readers to marvel at the intricacies of planetary dynamics and the cosmic forces that have shaped these majestic rings over eons.\n" +
                            "\n" +
                            "The exploration continues with a second image, offering a glimpse into the technological marvels of space missions capturing images of distant planets. A spacecraft, equipped with cutting-edge imaging technology, becomes the eyes through which humanity witnesses the awe-inspiring landscapes of alien worlds. The image unfolds as the spacecraft navigates the cosmos, transmitting visuals that reveal the unique features and mysteries concealed within the atmospheres and terrains of distant planets.\n" +
                            "\n" +
                            "As we delve into the depths of space, the secondary title, \"Exploring Planets and Moons,\" becomes a guiding beacon. Each paragraph unveils a new facet of cosmic exploration, where planets and moons become characters in an ongoing celestial saga. The article's narrative, seamlessly woven with captivating imagery, invites readers to join the quest for knowledge, as space missions continue to unveil the mysteries of our solar system.\n" +
                            "\n" +
                            "In conclusion, \"The Wonders of Our Solar System\" serves as a celestial guide, offering a captivating journey through the cosmic tapestry that envelops us. With Saturn's rings as a celestial jewel and spacecraft capturing distant planets in their cosmic dance, the article paints a vivid picture of our solar system's majesty. The mysteries yet to be unraveled beckon us to gaze into the cosmos, inspiring awe and wonder as we continue our exploration of the cosmic wonders that define our place in the universe.")
                    .secondaryTitle("Unveiling Cosmic Secrets through Planets and Moons")
                    .mainImg("https://images.pexels.com/photos/6508135/pexels-photo-6508135.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("View of Red Planet from Space")
                    .mainImgCredit("pexels, Frank Cone")
                    .secondImg("https://images.pexels.com/photos/34107/milky-way-stars-night-sky.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Cactus Plants Under the Starry Sky")
                    .secondImgCredit("Pixabay")
                    .build();

            Article entity5 = modelMapper.map(dto5, Article.class);
            entity5.setUser(user);
            entity5.setDate(LocalDate.now());
            articleRepository.save(entity5);


            ArticleRequestDto dto6 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("The Secrets of Plant Adaptations to Extreme Environments")
                    .content("In the realm of biology, we embark on a fascinating exploration of plant adaptations to the most extreme environments on our planet. From the icy tundras of the Arctic to the scorching deserts of Africa, plants have evolved intricate mechanisms to not just survive but thrive amidst nature's harshest challenges. This article unveils the secrets encoded in the very essence of these resilient organisms, offering a glimpse into the astounding world of botanical survival strategies.\n" +
                            "\n" +
                            "The first image introduces us to the tenacious adaptations of desert plants. Against the backdrop of arid landscapes, these botanical marvels have evolved to withstand the relentless sun and scarce water supply. The image, a snapshot of nature's resilience, captures the intricate adaptations—perhaps drought-resistant leaves or specialized root systems—that enable these plants to flourish in the unforgiving embrace of the desert.\n" +
                            "\n" +
                            "Moving to the second image, we venture into the Arctic, where plant life faces an entirely different set of challenges. The image showcases Arctic plants in extreme conditions, their hardiness on display amidst frigid temperatures and inhospitable terrains. As we delve into the image, we witness the resilience of Arctic flora, perhaps with adaptations such as antifreeze-like compounds or unique structural features that enable them to endure the harsh polar environment.\n" +
                            "\n" +
                            "The secondary title, \"Surviving Nature's Challenges,\" encapsulates the essence of this botanical odyssey. Each paragraph peels back the layers of adaptation, unraveling the intricate dance between plants and their environments. It is a testament to the resilience encoded in the genetic fabric of these organisms, a silent symphony of survival that has played out over millennia.\n" +
                            "\n" +
                            "As we traverse the diverse landscapes, from deserts to the Arctic, the article beckons readers to marvel at the ingenuity of plant life. Through captivating images and insightful narratives, we are invited to appreciate the delicate balance between adaptation and survival that has allowed these organisms to conquer the most extreme corners of our planet.\n" +
                            "\n" +
                            "In conclusion, \"The Secrets of Plant Adaptations to Extreme Environments\" serves as a botanical revelation, unlocking the mysteries of how plants navigate and thrive in nature's harshest realms. With images depicting adaptations in deserts and the Arctic, the article provides a visual and intellectual journey into the resilient world of plant life, inviting readers to ponder the remarkable strategies that have allowed these organisms to conquer the challenges posed by extreme environments.")
                    .secondaryTitle("Surviving Nature's Challenges")
                    .mainImg("https://images.pexels.com/photos/1151418/pexels-photo-1151418.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Selective Focus Photo of Green Plant Seedling on Tree Trunk")
                    .mainImgCredit("pexels, David Alberto Carmona Coto")
                    .secondImg("https://images.pexels.com/photos/5641346/pexels-photo-5641346.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Snowy landscape covered with snow and fir trees")
                    .secondImgCredit("pexels, Nico Becker")
                    .build();

            Article entity6 = modelMapper.map(dto6, Article.class);
            entity6.setUser(user);
            entity6.setDate(LocalDate.now());
            articleRepository.save(entity6);


            ArticleRequestDto dto7 = ArticleRequestDto.builder()
                    .category("Chemistry")
                    .title("The Dance of Molecules: Understanding Chemical Reactions")
                    .content("In the intricate realm of chemistry, where the dance of molecules shapes the very fabric of our existence, this article invites you to explore the fascinating choreography of chemical reactions. Within the controlled chaos of laboratory flasks and the subtle interactions within living organisms, we unravel the fundamental principles that govern the transformations of matter.\n" +
                            "\n" +
                            "The main image captures a moment in the grand ballet of molecules—a chemical reaction in a laboratory flask. Illuminated by the glow of scientific inquiry, this visual tableau hints at the intricate steps and partner exchanges that occur on the molecular stage. As we peer into this snapshot of molecular choreography, we are reminded of the beauty and precision underlying the seemingly chaotic world of chemical reactions.\n" +
                            "\n" +
                            "Transitioning to the second image, we shift our focus to the intricate realm of biochemical reactions within living organisms. The image provides a glimpse into the inner workings of the molecular ballet, where biological molecules engage in a delicate dance to sustain life. It is a choreography written in the language of enzymes, proteins, and cellular pathways—an exquisite performance that unfolds within the intricate machinery of living cells.\n" +
                            "\n" +
                            "The secondary title, \"Molecular Choreography,\" encapsulates the essence of this exploration. Each paragraph peels back the layers of understanding, unveiling the steps and nuances of molecular dances. It is an invitation to witness the elegance of chemical reactions, from the controlled environments of laboratories to the dynamic landscapes of living organisms.\n" +
                            "\n" +
                            "As we traverse the landscapes of molecular choreography, the article beckons readers to appreciate the profound impact of chemical reactions on our daily lives. From the synthesis of essential compounds to the metabolic dances within our bodies, the dance of molecules is a universal phenomenon that shapes the world around us.\n" +
                            "\n" +
                            "In conclusion, \"The Dance of Molecules: Understanding Chemical Reactions\" serves as a captivating journey into the heart of chemistry. Through visual representations of reactions in a laboratory flask and biochemical processes within living organisms, the article offers a nuanced exploration of molecular choreography. It is an ode to the elegance and significance of chemical reactions, inviting readers to join the dance and marvel at the intricate steps that govern the transformations of matter.")
                    .secondaryTitle("Molecular Choreography")
                    .mainImg("https://images.pexels.com/photos/10187636/pexels-photo-10187636.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("A Shot of Chemistry Flask With Chemistry Reaction in It")
                    .mainImgCredit("pexels, Ron Lach")
                    .secondImg("https://images.pexels.com/photos/3825366/pexels-photo-3825366.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Preparation for chemical experiment with test tubes and flask")
                    .secondImgCredit("pexels, RF._.studio")
                    .build();


            Article entity7 = modelMapper.map(dto7, Article.class);
            entity7.setUser(user);
            entity7.setDate(LocalDate.now());
            articleRepository.save(entity7);


            ArticleRequestDto dto8 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("The Quest for Dark Matter: Unraveling the Universe's Greatest Mystery")
                    .content("Embark on a cosmic journey into the mysterious realms of the universe with \"The Quest for Dark Matter: Unraveling the Universe's Greatest Mystery.\" This article delves into the enigma of dark matter, an elusive substance that constitutes a substantial portion of our cosmic tapestry. Astrophysicists worldwide are gripped by a quest to decipher the nature of dark matter, unraveling its effects on galaxies and cosmic structures.\n" +
                            "\n" +
                            "The main image captures the cosmic dance influenced by dark matter—galactic structures bathed in the cosmic shadows. Illuminated by the ethereal glow of distant stars, these structures bear the imprint of the unseen force, inviting contemplation on the vast cosmic ballet orchestrated by dark matter. The image, credited to Michael Adams, provides a visual portal into the cosmic mysteries that captivate the scientific community.\n" +
                            "\n" +
                            "Transitioning to the second image, we peer behind the scenes of scientific inquiry. Scientists, adorned in the attire of exploration, engage in experiments to probe the depths of dark matter. Laura White's image encapsulates the essence of relentless exploration and intellectual pursuit, as researchers work diligently to shed light on the shadowy substance that shapes the fabric of the cosmos.\n" +
                            "\n" +
                            "The secondary title, \"Probing the Cosmic Shadows,\" serves as a metaphorical key to understanding the article's narrative. Each paragraph unfolds like a cosmic detective story, revealing the clues and evidence amassed by scientists in their relentless pursuit of unraveling dark matter's secrets. It is an invitation to join the quest, a cosmic adventure that challenges our understanding of the fundamental forces governing the universe.\n" +
                            "\n" +
                            "As we navigate the cosmic shadows, the article underscores the profound implications of dark matter on the fabric of the cosmos. From gravitational interactions to the formation of galactic structures, dark matter's influence permeates the vast expanse of space, leaving an indelible mark on our cosmic journey.\n" +
                            "\n" +
                            "In conclusion, \"The Quest for Dark Matter\" beckons readers to ponder the mysteries that shroud our universe. Through captivating visuals and a narrative that probes the cosmic shadows, the article invites us to partake in the scientific odyssey dedicated to unraveling the universe's greatest mystery. It is a cosmic quest that transcends the boundaries of our understanding, beckoning us to explore the unseen forces that shape the grand tapestry of the cosmos.")
                    .secondaryTitle("Probing the Cosmic Shadows")
                    .mainImg("https://images.pexels.com/photos/924824/pexels-photo-924824.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Time Lapse Photo of Stars on Night")
                    .mainImgCredit("pexels, Jakub Novacek")
                    .secondImg("https://images.pexels.com/photos/806763/pexels-photo-806763.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Genf, GE, Switzerland")
                    .secondImgCredit("pexels, Pietro Battistoni")
                    .build();

            Article entity8 = modelMapper.map(dto8, Article.class);
            entity8.setUser(user);
            entity8.setDate(LocalDate.now());
            articleRepository.save(entity8);


            ArticleRequestDto dto9 = ArticleRequestDto.builder()
                    .category("Space")
                    .title("Journey to the Stars: The Marvels of Interstellar Exploration")
                    .content("Embark on a celestial odyssey as we explore the wonders of interstellar space in \"Journey to the Stars: The Marvels of Interstellar Exploration.\" This article propels us into the realm of cosmic imagination, contemplating the possibilities and challenges of venturing beyond our solar system.\n" +
                            "\n" +
                            "As we commence our interstellar journey, the main image captures the essence of human ambition—a conceptual artwork of an interstellar spacecraft. Crafted by the visionary Alex Turner, the image invites us to envision a future where humanity stretches its reach into the cosmic expanse. The spacecraft, adorned with sleek lines and futuristic technology, becomes a vessel of dreams, propelling us toward distant stars.\n" +
                            "\n" +
                            "The accompanying main image description, \"Conceptual artwork of an interstellar spacecraft,\" sets the stage for the article's narrative. It signals the reader to prepare for a journey beyond the familiar boundaries of our solar system, where the laws of physics and the vastness of space pose both challenges and opportunities.\n" +
                            "\n" +
                            "Transitioning to the second image, we glimpse the beauty of distant exoplanets in a faraway star system. Emma Davis's captivating image captures the allure of these cosmic orbs, swirling in the cosmic dance of interstellar space. It serves as a visual testament to the marvels awaiting exploration, prompting contemplation on the diversity of worlds that may exist beyond our solar neighborhood.\n" +
                            "\n" +
                            "The secondary title, \"The Interstellar Odyssey,\" encapsulates the spirit of the article. Each paragraph unfolds like a chapter in an epic tale, chronicling the challenges, triumphs, and mysteries encountered on our interstellar journey. It's an odyssey that extends beyond the limits of our current understanding, prompting readers to join the cosmic exploration with a sense of wonder and curiosity.\n" +
                            "\n" +
                            "While the content is intentionally left blank in the provided structure, the overarching narrative of the article can delve into topics such as spacecraft propulsion technologies, potential destinations in interstellar space, and the quest for signs of extraterrestrial life. The article unfolds as a cosmic roadmap, guiding readers through the intricacies of interstellar exploration and igniting their imagination about the marvels that await us among the stars.\n" +
                            "\n" +
                            "In essence, \"Journey to the Stars\" beckons readers to transcend the familiar and embark on a celestial odyssey. Through striking visuals, a compelling narrative, and the spirit of exploration, the article invites us to dream of a future where humanity's reach extends far beyond the confines of our solar system, into the boundless wonders of interstellar space.")
                    .secondaryTitle("The Interstellar Odyssey")
                    .mainImg("https://images.pexels.com/photos/631477/pexels-photo-631477.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Silhouette of Trees during Nighttime")
                    .mainImgCredit("pexels, Neale LaSalle")
                    .secondImg("https://images.pexels.com/photos/1169754/pexels-photo-1169754.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Milky Way Illustration")
                    .secondImgCredit("pexels, Philippe Donn")
                    .build();

            Article entity9 = modelMapper.map(dto9, Article.class);
            entity9.setUser(user);
            entity9.setDate(LocalDate.now());
            articleRepository.save(entity9);


            ArticleRequestDto dto10 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("Unraveling the Genetic Code: A Journey into Human DNA")
                    .content("Embark on an enlightening journey into the intricate world of human DNA in \"Unraveling the Genetic Code: A Journey into Human DNA.\" This exploration delves into the fundamental aspects of our genetic blueprint, from the mesmerizing structure of the DNA double helix to the groundbreaking discoveries that shape our understanding of life itself.\n" +
                            "\n" +
                            "The primary image, an illustration of the DNA double helix, serves as a visual gateway into the fascinating world of genetics. Crafted by the talented Sarah Johnson, this image captures the elegance and complexity of DNA—the molecule that encodes the instructions for life. The double helix, an iconic symbol of genetics, unravels the secrets held within our genes and sets the stage for a profound exploration.\n" +
                            "\n" +
                            "Accompanying this captivating image is the main image description, \"Illustration of DNA double helix.\" This concise caption guides the reader's focus to the core subject matter, signaling the commencement of a journey that will unravel the mysteries encoded in the strands of our genetic material.\n" +
                            "\n" +
                            "Transitioning to the second image, we peer into the realm of scientific inquiry as a dedicated researcher examines DNA under a microscope. Michael Brown's evocative image captures the essence of discovery, portraying the meticulous examination of genetic material that unveils the intricacies of our biological inheritance.\n" +
                            "\n" +
                            "The secondary title, \"Decoding the Blueprint,\" encapsulates the essence of the article. Each paragraph unfolds like a strand of DNA, guiding readers through the history of genetic exploration, from the discovery of the double helix by Watson and Crick to the modern era of genomics. It's a journey that invites readers to contemplate the profound impact of genetic knowledge on medicine, biotechnology, and our understanding of human evolution.\n" +
                            "\n" +
                            "While the content is intentionally left blank in the provided structure, the overarching narrative of the article can delve into topics such as the structure of DNA, the role of genes in heredity, and the revolutionary advancements in gene-editing technologies. The article unfolds as a genetic narrative, decoding the blueprint of human life and offering insights into the ongoing quest to unravel the secrets embedded in our DNA.\n" +
                            "\n" +
                            "In essence, \"Unraveling the Genetic Code\" beckons readers to explore the very essence of their being—the genetic instructions that shape who we are. Through captivating visuals, informative content, and the spirit of scientific inquiry, the article invites us to embark on a journey into the heart of our genetic code, unlocking the mysteries that connect us to the intricate web of life.")
                    .secondaryTitle("Decoding the Blueprint")
                    .mainImg("https://cdn.pixabay.com/photo/2018/07/15/10/44/dna-3539309_1280.jpg")
                    .mainImgDescription("Illustration of DNA double helix")
                    .mainImgCredit("Pixabay")
                    .secondImg("https://images.pexels.com/photos/11198507/pexels-photo-11198507.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Cell Seen Under Microscope")
                    .secondImgCredit("pexels, Fayette Reynolds M.S.")
                    .build();

            Article entity10 = modelMapper.map(dto10, Article.class);
            entity10.setUser(user);
            entity10.setDate(LocalDate.now());
            articleRepository.save(entity10);


            ArticleRequestDto dto11 = ArticleRequestDto.builder()
                    .category("Chemistry")
                    .title("The Alchemy of Elements: Transformations in the Periodic Table")
                    .content("Embark on a fascinating journey into the world of chemistry with \"The Alchemy of Elements: Transformations in the Periodic Table.\" This captivating exploration delves into the intricate dance of elements, where various substances undergo remarkable transformations, shaping the very fabric of our world.\n" +
                            "\n" +
                            "The primary image, a mesmerizing snapshot by Peter White, captures chemical reactions involving various elements. The image serves as a visual overture, hinting at the dynamic and transformative processes that occur at the molecular level. The vivid portrayal of these reactions invites you to witness the alchemical magic unfolding in laboratories, as elements engage in a complex interplay, giving rise to new substances.\n" +
                            "\n" +
                            "The main image description, \"Chemical reactions involving various elements,\" acts as a guide, directing your attention to the heart of the article. It sets the stage for a journey into the periodic table, where elements, each with its unique properties, participate in a symphony of reactions that define the essence of chemistry.\n" +
                            "\n" +
                            "Transitioning to the second image, courtesy of Emma Turner, we delve into the realm of laboratory apparatus designed for elemental analysis. This image encapsulates the precision and sophistication of scientific instruments employed in the study of elemental transformations. It serves as a visual representation of the tools that enable chemists to unravel the mysteries of the periodic table and explore the properties of different elements.\n" +
                            "\n" +
                            "The secondary title, \"Elemental Transformations,\" encapsulates the core theme of the article. Each paragraph unfolds like a chapter in the story of the periodic table, guiding you through the history of elemental discoveries, the principles of chemical reactions, and the role of elements in our daily lives. It's a journey that unveils the wonders of alchemy, not as an ancient mysticism but as a vibrant and continually evolving science.\n" +
                            "\n" +
                            "While the content is intentionally left blank in the provided structure, the overarching narrative of the article can explore topics such as the historical development of the periodic table, key figures in the field of chemistry, and the applications of elemental transformations in various industries. The article unfolds as a celebration of the diversity and transformative power inherent in the elements that constitute our universe.\n" +
                            "\n" +
                            "In essence, \"The Alchemy of Elements\" invites you to witness the dynamic interplay of elements in the grand symphony of chemistry. Through captivating visuals, informative content, and a celebration of scientific inquiry, the article beckons you to embark on a journey into the heart of the periodic table, where the alchemy of elements continues to shape our understanding of the fundamental building blocks of matter.")
                    .secondaryTitle("Elemental Transformations")
                    .mainImg("https://images.pexels.com/photos/954585/pexels-photo-954585.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Two Test Tubes")
                    .mainImgCredit("Pexels, Martin Lopez")
                    .secondImg("https://images.pexels.com/photos/276205/pexels-photo-276205.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Spider Web With Dew Vector Art\n")
                    .secondImgCredit("Pixabay")
                    .build();

            Article entity11 = modelMapper.map(dto11, Article.class);
            entity11.setUser(user);
            entity11.setDate(LocalDate.now());
            articleRepository.save(entity11);


            ArticleRequestDto dto12 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("Einstein's Legacy: The Continuum of Space-Time")
                    .content("Embark on a profound exploration of the cosmos with \"Einstein's Legacy: The Continuum of Space-Time,\" a captivating journey into the revolutionary theories of one of the greatest minds in physics. This article delves into the profound impact of Albert Einstein's theories of relativity, unveiling the mysteries of space-time curvature, gravitational waves, and the intricate fabric that defines our universe.\n" +
                            "\n" +
                            "The primary image, a striking illustration by David Miller, depicts the curvature of space-time around a massive object. This visual representation serves as a gateway into the complex concepts explored in the article. It encapsulates the essence of Einstein's groundbreaking theories, inviting readers to contemplate the bending and warping of the very fabric of space and time.\n" +
                            "\n" +
                            "The main image description, \"Illustration of space-time curvature around a massive object,\" acts as a guide, directing attention to the gravitational ballet of celestial bodies. This image is a visual testament to the profound impact of massive objects on the space-time continuum, a concept that reshaped our understanding of gravity and the nature of the cosmos.\n" +
                            "\n" +
                            "Transitioning to the second image, courtesy of Laura Green, we peer into the realm of observatories capturing gravitational waves. This image captures the essence of modern astrophysics, where cutting-edge technology allows scientists to observe and measure the ripples in space-time caused by the most cataclysmic events in the universe.\n" +
                            "\n" +
                            "The secondary title, \"Curvature of the Cosmos,\" encapsulates the overarching theme of the article. Each paragraph unfurls as a chapter in the narrative, exploring the historical context of Einstein's theories, the experimental validations of his predictions, and the ongoing advancements in gravitational wave astronomy.\n" +
                            "\n" +
                            "While the content is intentionally left blank in the provided structure, the article can delve into topics such as the development of Einstein's theories, the impact of relativity on our understanding of the universe, and the groundbreaking discoveries made possible by the observation of gravitational waves. The article unfolds as a tribute to Einstein's enduring legacy, inviting readers to contemplate the profound connections between space, time, and gravity.\n" +
                            "\n" +
                            "In essence, \"Einstein's Legacy\" beckons you to traverse the continuum of space-time, inviting you to explore the cosmic tapestry woven by the visionary mind of Albert Einstein. Through captivating visuals, insightful content, and a celebration of scientific discovery, the article invites you to embark on a journey into the depths of the cosmos, where the curvature of the universe reveals its secrets.")
                    .secondaryTitle("Curvature of the Cosmos")
                    .mainImg("https://images.pexels.com/photos/714699/pexels-photo-714699.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Person Holding a Chalk in Front of the Chalk Board\n")
                    .mainImgCredit("JESHOOTS.com")
                    .secondImg("https://images.pexels.com/photos/4460676/pexels-photo-4460676.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Huge cooling towers in nuclear power plant\n")
                    .secondImgCredit("Pexels, Johannes Plenio")
                    .build();

            Article entity12 = modelMapper.map(dto12, Article.class);
            entity12.setUser(user);
            entity12.setDate(LocalDate.now());
            articleRepository.save(entity12);


            ArticleRequestDto dto13 = ArticleRequestDto.builder()
                    .category("Space")
                    .title("Mars: The Red Planet's Mysteries Revealed")
                    .content("Embark on a celestial odyssey with \"Mars: The Red Planet's Mysteries Revealed,\" a captivating exploration of the enigmatic world that has fascinated astronomers and space enthusiasts for centuries. This article unveils the mysteries of Mars, from its captivating red hue to the tantalizing possibility of past or present life on its surface.\n" +
                            "\n" +
                            "The primary image, a panoramic view of Mars' surface captured by the talented Alex Davis, serves as a visual gateway into the article. This captivating image invites readers to immerse themselves in the rugged terrain of the Martian landscape, showcasing the planet's unique geological features and inviting contemplation about its geological history.\n" +
                            "\n" +
                            "The main image description, \"Panoramic view of Mars' surface,\" acts as a guide, directing attention to the expansive vistas that stretch across the Martian plains. This image captures the essence of the Martian landscape, inviting readers to envision standing on the surface of the Red Planet, surrounded by its iconic red rocks and dusty horizons.\n" +
                            "\n" +
                            "Transitioning to the second image, courtesy of Sarah Turner, we venture into the world of Mars exploration with a captivating glimpse of a rover traversing the Martian landscape. This image encapsulates the spirit of scientific curiosity and human ingenuity as we strive to unravel the secrets hidden beneath the Martian soil.\n" +
                            "\n" +
                            "The secondary title, \"Red Planet Odyssey,\" encapsulates the overarching theme of the article. Each paragraph unfolds as a chapter in the narrative, exploring the geological features of Mars, the history of Martian exploration, and the ongoing missions that seek to unveil the planet's mysteries.\n" +
                            "\n" +
                            "While the content is intentionally left blank in the provided structure, the article can delve into topics such as the geological history of Mars, the evidence for water on the planet, and the latest discoveries made by rovers and orbiters. The article unfolds as a thrilling journey into the heart of the Red Planet, inviting readers to ponder the possibility of life beyond Earth and the role Mars plays in our understanding of the solar system.\n" +
                            "\n" +
                            "In essence, \"Mars: The Red Planet's Mysteries Revealed\" beckons you to join the exploration of a world that has captivated human imagination for centuries. Through captivating visuals, insightful content, and a celebration of the ongoing missions to Mars, the article invites you to embark on a celestial odyssey where the mysteries of the Red Planet are waiting to be revealed.")
                    .secondaryTitle("Red Planet Odyssey")
                    .mainImg("https://images.pexels.com/photos/73910/mars-mars-rover-space-travel-robot-73910.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Gray and White Robot")
                    .mainImgCredit("Pixabay")
                    .secondImg("https://images.pexels.com/photos/8474500/pexels-photo-8474500.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Dry Rocky Land Under a Gloomy Sky")
                    .secondImgCredit("pexels, RDNE Stock project")
                    .build();

            Article entity13 = modelMapper.map(dto13, Article.class);
            entity13.setUser(user);
            entity13.setDate(LocalDate.now());
            articleRepository.save(entity13);


            ArticleRequestDto dto14 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("Exploring the Frontiers of Bioinformatics")
                    .content("Bioinformatics, a dynamic field at the intersection of biology and technology, has emerged as a powerful tool in unraveling the complexities of the biological world. As scientists delve into the vast realm of bioinformatics, they embark on a journey to unlock biological mysteries through the meticulous analysis of data.\n" +
                            "\n" +
                            "The utilization of cutting-edge technologies in bioinformatics opens new frontiers for understanding the intricacies of living organisms. In the pursuit of knowledge, scientists employ sophisticated tools to analyze biological data, revealing insights that were once hidden beneath the surface. The main image captures a scientist immersed in the analytical process, symbolizing the dedication and precision required to navigate the frontiers of bioinformatics.\n" +
                            "\n" +
                            "DNA sequencing, a cornerstone of bioinformatics, takes center stage in the exploration of biological mysteries. The second image showcases the marvel of DNA sequencing technology in action, highlighting the intricate processes that decode the genetic information embedded in every living organism. As researchers peer into the fundamental building blocks of life, they gain a deeper understanding of genetics, paving the way for breakthroughs in medicine, agriculture, and beyond.\n" +
                            "\n" +
                            "Bioinformatics not only sheds light on the structure of genetic information but also plays a pivotal role in understanding the functions and interactions within biological systems. By deciphering the language of DNA, scientists can identify potential therapeutic targets, predict the behavior of diseases, and explore novel avenues for biotechnological advancements.\n" +
                            "\n" +
                            "The journey into bioinformatics is marked by collaboration between biology and technology, where scientists leverage computational tools to process vast datasets and extract meaningful patterns. This interdisciplinary approach accelerates the pace of discovery, propelling the field toward new horizons.\n" +
                            "\n" +
                            "In conclusion, \"Exploring the Frontiers of Bioinformatics\" encapsulates the spirit of scientific inquiry as researchers harness the power of data to unravel the mysteries of life. From the scientist engrossed in data analysis to the intricate processes of DNA sequencing, the article invites readers to join the exciting exploration of the bioinformatics frontier, where each data point holds the potential to transform our understanding of the biological world.")
                    .secondaryTitle("Unlocking Biological Mysteries through Data")
                    .mainImg("https://images.pexels.com/photos/1076758/pexels-photo-1076758.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Shallow Focus Photo of Pink and Brown Jellyfish")
                    .mainImgCredit("pexels, Pawel Kalisinski")
                    .secondImg("https://images.pexels.com/photos/954583/pexels-photo-954583.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Person Putting a Drop on Test Tube\n")
                    .secondImgCredit("pexels, Martin Lopez")
                    .build();

            Article entity14 = modelMapper.map(dto14, Article.class);
            entity14.setUser(user);
            entity14.setDate(LocalDate.now());
            articleRepository.save(entity14);


            ArticleRequestDto dto15 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("Biosensors: Revolutionizing Medical Diagnostics")
                    .content("Biosensors, at the forefront of medical innovation, represent a groundbreaking synergy between biology and sensor technology. This dynamic integration has ushered in a new era of medical diagnostics, where precision meets efficiency to revolutionize the way we monitor and understand the human body.\n" +
                            "\n" +
                            "The main image provides a close-up view of a biosensor in a laboratory setting, showcasing the intricate technology that underpins this revolutionary medical tool. Biosensors are designed to detect and analyze biological markers, offering real-time insights into various physiological processes. This sophisticated technology plays a pivotal role in medical diagnostics, enabling early detection of diseases and providing clinicians with valuable data for personalized patient care.\n" +
                            "\n" +
                            "In the realm of biosensors, the marriage of biology and sensor technology has paved the way for point-of-care diagnostics. The second image captures a doctor utilizing a biosensor for patient diagnosis, highlighting the practical applications of this transformative technology in a clinical setting. The immediacy and accuracy of biosensor data empower healthcare professionals to make informed decisions swiftly, ultimately enhancing patient outcomes.\n" +
                            "\n" +
                            "The versatility of biosensors extends beyond traditional medical diagnostics. These innovative devices find applications in environmental monitoring, food safety, and biotechnology, amplifying their impact across diverse fields. As the technology continues to evolve, biosensors hold the potential to redefine the landscape of personalized medicine, providing tailored solutions for individual health profiles.\n" +
                            "\n" +
                            "The integration of biology and sensor technology in biosensors represents a paradigm shift in medical diagnostics, offering a glimpse into the future of healthcare. With their ability to provide real-time, actionable data, biosensors stand as a testament to the relentless pursuit of innovation in the service of human well-being.")
                    .secondaryTitle("The Integration of Biology and Sensor Technology")
                    .mainImg("https://images.pexels.com/photos/3938022/pexels-photo-3938022.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Scientist Using Microscope")
                    .mainImgCredit("pexels, Chokniti Khongchum")
                    .secondImg("https://images.pexels.com/photos/5428147/pexels-photo-5428147.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Human Skeleton Model\n")
                    .secondImgCredit("pexels, Tima Miroshnichenko")
                    .build();

            Article entity15 = modelMapper.map(dto15, Article.class);
            entity15.setUser(user);
            entity15.setDate(LocalDate.now());
            articleRepository.save(entity15);


            ArticleRequestDto dto16 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("Genomic Medicine: Personalized Healthcare Solutions")
                    .content("Genomic Medicine stands at the forefront of a healthcare revolution, offering personalized solutions that are tailored to an individual's unique genetic makeup. This cutting-edge field harnesses the power of genetic information to redefine the way we approach healthcare, from diagnostics to treatment strategies.\n" +
                            "\n" +
                            "In the main image, a doctor is engaged in a discussion about genomic medicine with a patient, symbolizing the personalized and patient-centric nature of this innovative approach. Genomic data provides insights into an individual's genetic predispositions, allowing healthcare professionals to customize treatments and interventions for enhanced efficacy.\n" +
                            "\n" +
                            "The second image captures a geneticist examining DNA sequences, highlighting the intricate process of decoding genetic information. Genomic medicine involves the comprehensive analysis of an individual's DNA, identifying genetic variations that may influence health and disease. This detailed examination empowers healthcare providers to make informed decisions about treatment plans, medication choices, and preventive measures.\n" +
                            "\n" +
                            "Genomic medicine's impact extends across various medical disciplines, including oncology, cardiology, and rare diseases. By understanding the genetic basis of diseases, healthcare professionals can prescribe targeted therapies, minimizing adverse effects and optimizing outcomes. Additionally, genomic medicine plays a pivotal role in preventive care, allowing for early detection of genetic predispositions and proactive interventions.\n" +
                            "\n" +
                            "As we delve deeper into the era of personalized medicine, genomic medicine emerges as a beacon of hope for improved patient outcomes. The fusion of genetic insights with medical expertise opens new frontiers in healthcare, ushering in an era where treatments are as unique as the individuals they aim to heal.")
                    .secondaryTitle("Tailoring Treatments Based on Genetic Information")
                    .mainImg("https://images.pexels.com/photos/2280571/pexels-photo-2280571.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Person Holding Laboratory Flask")
                    .mainImgCredit("pexels, Chokniti Khongchum")
                    .secondImg("https://images.pexels.com/photos/1366942/pexels-photo-1366942.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Photo of Clear Glass Measuring Cup Lot\n")
                    .secondImgCredit("Rodolfo Clix")
                    .build();

            Article entity16 = modelMapper.map(dto16, Article.class);
            entity16.setUser(user);
            entity16.setDate(LocalDate.now());
            articleRepository.save(entity16);


            ArticleRequestDto dto17 = ArticleRequestDto.builder()
                    .category("Tech")
                    .title("The Rise of Quantum Computing: A Technological Revolution")
                    .content("The advent of Quantum Computing marks a paradigm shift in the realm of information processing, propelling us into a new era of computational capabilities. This groundbreaking technology harnesses the principles of quantum mechanics to perform computations at speeds and complexities previously deemed impossible.\n" +
                            "\n" +
                            "In the main image, a quantum computer is showcased in a laboratory setting, capturing the essence of this revolutionary technology. Unlike classical computers that rely on bits, quantum computers leverage qubits, exploiting the unique quantum properties of superposition and entanglement. This allows them to perform intricate calculations exponentially faster than their classical counterparts.\n" +
                            "\n" +
                            "The second image provides a glimpse into the meticulous calibration of a quantum processor by an engineer. Quantum computing requires precision at the quantum level, and engineers play a crucial role in ensuring the stability and accuracy of quantum systems. The visual showcases the intersection of advanced technology and human expertise driving the quantum computing revolution.\n" +
                            "\n" +
                            "Quantum computing holds immense promise across various domains, from cryptography and optimization problems to drug discovery and artificial intelligence. Its potential to solve complex problems that were once insurmountable opens avenues for scientific discovery and technological advancements.\n" +
                            "\n" +
                            "As we stand on the brink of a technological revolution, the rise of quantum computing reshapes our understanding of what is computationally achievable. The integration of quantum technologies into our computing landscape heralds a future where computational boundaries are pushed, unlocking unprecedented possibilities for innovation and problem-solving.")
                    .secondaryTitle("Harnessing Quantum Mechanics for Advanced Computing")
                    .mainImg("https://images.pexels.com/photos/1714208/pexels-photo-1714208.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Black Flat Screen Computer Monitor")
                    .mainImgCredit("pexels, Josh Sorenson")
                    .secondImg("https://images.pexels.com/photos/257881/pexels-photo-257881.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Turned-off Vintage White and Black Computer")
                    .secondImgCredit("Pixabay")
                    .build();

            Article entity17 = modelMapper.map(dto17, Article.class);
            entity17.setUser(user);
            entity17.setDate(LocalDate.now());
            articleRepository.save(entity17);


            ArticleRequestDto dto18 = ArticleRequestDto.builder()
                    .category("Tech")
                    .title("Augmented Reality: Shaping the Future of Interaction")
                    .title("In the ever-evolving landscape of technology, Augmented Reality (AR) emerges as a transformative force, redefining how we interact with the digital and physical worlds. This cutting-edge technology seamlessly blends virtual elements with the real environment, creating immersive and interactive experiences.\n" +
                            "\n" +
                            "The main image captures a person wearing augmented reality glasses, delving into an immersive digital overlay. This illustration epitomizes the potential of AR to enhance our daily lives, from entertainment and gaming to education and workplace applications. Augmented reality glasses open portals to a world where information and digital content seamlessly coexist with the tangible surroundings.\n" +
                            "\n" +
                            "In the second image, the application of AR in industrial design is showcased. An engineer employs AR technology to visualize and manipulate digital prototypes overlaid onto physical objects. This demonstrates the practical applications of AR in enhancing design processes, allowing for real-time adjustments and improvements.\n" +
                            "\n" +
                            "Augmented Reality's impact extends across diverse sectors, including education, healthcare, and manufacturing. Its ability to merge the virtual and physical realms presents opportunities for innovative solutions and improved user experiences. As AR technology continues to advance, we can anticipate a future where interactive digital elements become an integral part of our daily routines.")
                    .secondaryTitle("Immersive Experiences with AR Technology")
                    .mainImg("https://images.pexels.com/photos/3861969/pexels-photo-3861969.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Code Projected Over Woman")
                    .mainImgCredit("Pexels, ThisIsEngineering")
                    .secondImg("https://images.pexels.com/photos/123335/pexels-photo-123335.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Woman Using Vr Goggles Outdoors")
                    .secondImgCredit("Pexels, Bradley Hook")
                    .build();

            Article entity18 = modelMapper.map(dto18, Article.class);
            entity18.setUser(user);
            entity18.setDate(LocalDate.now());
            articleRepository.save(entity18);

            ArticleRequestDto dto19 = ArticleRequestDto.builder()
                    .category("Tech")
                    .title("Artificial Intelligence in Everyday Life")
                    .content("Artificial Intelligence (AI) has transcended the realm of science fiction and seamlessly integrated into our daily lives, revolutionizing the way we experience the world around us. From smart homes to personal devices, AI's transformative influence is unmistakable.\n" +
                            "\n" +
                            "The main image captures the essence of AI in smart home devices. A futuristic living space is adorned with interconnected gadgets that leverage AI algorithms to enhance efficiency and convenience. Whether it's intelligent climate control, automated lighting, or predictive maintenance, AI-driven smart home devices create an environment that adapts to the needs and preferences of its occupants.\n" +
                            "\n" +
                            "In the second image, we witness the ubiquitous presence of AI through a virtual assistant on a smartphone. This AI-driven assistant responds to voice commands, provides personalized recommendations, and streamlines various tasks, making our daily interactions with technology more intuitive and efficient.\n" +
                            "\n" +
                            "AI's impact extends beyond individual gadgets, contributing to broader societal advancements. From healthcare diagnostics to traffic management, the integration of AI into everyday life is a testament to its versatility and transformative potential. As we continue to embrace AI technologies, our daily experiences are poised to become more interconnected, intelligent, and tailored to individual preferences.")
                    .secondaryTitle("How AI is Transforming Our Daily Experiences")
                    .mainImg("https://images.pexels.com/photos/3913025/pexels-photo-3913025.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Prosthetic Arm on Blue Background")
                    .mainImgCredit("Pexels, ThisIsEngineering")
                    .secondImg("https://images.pexels.com/photos/343457/pexels-photo-343457.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Green Circuit Board")
                    .secondImgCredit("Pexels, Miguel Á. Padriñán")
                    .build();

            Article entity19 = modelMapper.map(dto19, Article.class);
            entity19.setUser(user);
            entity19.setDate(LocalDate.now());
            articleRepository.save(entity19);

            ArticleRequestDto dto20 = ArticleRequestDto.builder()
                    .category("Tech")
                    .title("The Future of Robotics: Advancements and Applications")
                    .content("Artificial Intelligence in Everyday Life\":\n" +
                            "\n" +
                            "Artificial Intelligence (AI) has transcended the realm of science fiction and seamlessly integrated into our daily lives, revolutionizing the way we experience the world around us. From smart homes to personal devices, AI's transformative influence is unmistakable.\n" +
                            "\n" +
                            "The main image captures the essence of AI in smart home devices. A futuristic living space is adorned with interconnected gadgets that leverage AI algorithms to enhance efficiency and convenience. Whether it's intelligent climate control, automated lighting, or predictive maintenance, AI-driven smart home devices create an environment that adapts to the needs and preferences of its occupants.\n" +
                            "\n" +
                            "In the second image, we witness the ubiquitous presence of AI through a virtual assistant on a smartphone. This AI-driven assistant responds to voice commands, provides personalized recommendations, and streamlines various tasks, making our daily interactions with technology more intuitive and efficient.\n" +
                            "\n" +
                            "AI's impact extends beyond individual gadgets, contributing to broader societal advancements. From healthcare diagnostics to traffic management, the integration of AI into everyday life is a testament to its versatility and transformative potential. As we continue to embrace AI technologies, our daily experiences are poised to become more interconnected, intelligent, and tailored to individual preferences.")
                    .secondaryTitle("Exploring the Role of Robotics in Various Industries")
                    .mainImg("https://images.pexels.com/photos/3912979/pexels-photo-3912979.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Person Holding Prosthetic Arm")
                    .mainImgCredit("Pexels, ThisIsEngineering")
                    .secondImg("https://images.pexels.com/photos/8566470/pexels-photo-8566470.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("White Robot Action Figure on Blue String Lights")
                    .secondImgCredit("Pexels, Kindel Media")
                    .build();

            Article entity20 = modelMapper.map(dto20, Article.class);
            entity20.setUser(user);
            entity20.setDate(LocalDate.now());
            articleRepository.save(entity20);

            ArticleRequestDto dto21 = ArticleRequestDto.builder()
                    .category("Tech")
                    .title("Cybersecurity in the Digital Age")
                    .content("In the rapidly evolving landscape of the digital age, the importance of cybersecurity cannot be overstated. As our world becomes increasingly interconnected, safeguarding sensitive information from cyber threats has become a paramount concern.\n" +
                            "\n" +
                            "The main image vividly portrays a hacker in a hood against a digital security background, symbolizing the constant challenges posed by cybercriminals. Cybersecurity experts are at the forefront, employing sophisticated measures to thwart unauthorized access, data breaches, and other malicious activities that could compromise the integrity of digital systems.\n" +
                            "\n" +
                            "The second image illustrates the concept of a secure connection in cybersecurity. In an era where data is constantly transmitted across networks, ensuring the confidentiality and integrity of information is imperative. Cybersecurity measures such as encryption, firewalls, and secure protocols play a crucial role in maintaining the trustworthiness of digital interactions.\n" +
                            "\n" +
                            "As businesses, governments, and individuals navigate the intricacies of the digital realm, the field of cybersecurity continues to evolve. Threats are becoming more sophisticated, necessitating innovative solutions and proactive strategies to stay one step ahead. Cybersecurity professionals are not only tasked with defending against attacks but also with anticipating and mitigating future risks.")
                    .secondaryTitle("Safeguarding Information in an Interconnected World")
                    .mainImg("https://images.pexels.com/photos/546819/pexels-photo-546819.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Close Up Photo of Programming of Codes")
                    .mainImgCredit("Pexels, luis gomes")
                    .secondImg("https://images.pexels.com/photos/699122/pexels-photo-699122.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Black Iphone 7 on Brown Table")
                    .secondImgCredit("Pexels, Tyler Lastovich")
                    .build();

            Article entity21 = modelMapper.map(dto21, Article.class);
            entity21.setUser(user);
            entity21.setDate(LocalDate.now());
            articleRepository.save(entity21);


            ArticleRequestDto dto22 = ArticleRequestDto.builder()
                    .category("Tech")
                    .title("5G Technology: Revolutionizing Connectivity")
                    .content("In the ever-evolving landscape of technology, 5G stands out as a transformative force, revolutionizing connectivity and communication. Represented by a 5G network tower against a cityscape in the main image, the visual encapsulates the impact of this cutting-edge technology on urban landscapes and beyond.\n" +
                            "\n" +
                            "5G technology is not merely an incremental upgrade; it represents a paradigm shift in how we connect and communicate. The secondary image showcases devices seamlessly connected to a high-speed 5G network, highlighting the efficiency and speed that this technology brings to our daily lives. Whether it's ultra-fast internet browsing, low-latency communication, or the enablement of emerging technologies like the Internet of Things (IoT), 5G is a game-changer.\n" +
                            "\n" +
                            "As we delve into the era of 5G connectivity, the implications span across various industries. From healthcare and education to smart cities and autonomous vehicles, the ripple effects of 5G are reshaping the possibilities of what technology can achieve.")
                    .secondaryTitle("Impact of 5G on Communication and Connectivity")
                    .mainImg("https://images.pexels.com/photos/2582937/pexels-photo-2582937.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Black and Gray Motherboard")
                    .mainImgCredit("Pexels, Athena")
                    .secondImg("https://images.pexels.com/photos/943096/pexels-photo-943096.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Photo of Turned on Laptop Computer\n")
                    .secondImgCredit("Pexels, Danny Meneses")
                    .build();

            Article entity22 = modelMapper.map(dto22, Article.class);
            entity22.setUser(user);
            entity22.setDate(LocalDate.now());
            articleRepository.save(entity22);

            ArticleRequestDto dto23 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("Ecological Balance: Biodiversity Conservation Strategies")
                    .content("In the intricate dance of nature, biodiversity plays a vital role in maintaining ecological balance. Titled 'Ecological Balance: Biodiversity Conservation Strategies,' this article explores the efforts and strategies employed to preserve diverse ecosystems for future generations. Researchers and conservationists are actively engaged in understanding and protecting the delicate relationships between different plant and animal species.\n" +
                            "\n" +
                            "The article delves into the importance of biodiversity conservation in sustaining ecosystems and highlights the challenges faced by various ecosystems worldwide. From rainforests to coral reefs, each habitat requires tailored strategies to address the threats posed by climate change, habitat loss, and human activities.\n" +
                            "\n" +
                            "Efforts in preserving biodiversity extend beyond scientific research. Conservation projects often involve collaboration with local communities and policymakers to create sustainable solutions. The article emphasizes the need for a collective approach to safeguarding these invaluable ecosystems for the well-being of our planet and future generations.")
                    .secondaryTitle("Preserving Ecosystems for Future Generations")
                    .mainImg("https://images.pexels.com/photos/920157/pexels-photo-920157.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Macro Photography of Bubble Coral")
                    .mainImgCredit("Pexels, Egor Kamelev")
                    .secondImg("https://images.pexels.com/photos/1059161/pexels-photo-1059161.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Underwater Photography of Green Jelly Fish")
                    .secondImgCredit("Pexels, Willy Arisky")
                    .build();

            Article entity23 = modelMapper.map(dto23, Article.class);
            entity23.setUser(user);
            entity23.setDate(LocalDate.now());
            articleRepository.save(entity23);

            ArticleRequestDto dto24 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("The Microbial World: Unseen Heroes of Life")
                    .content("In the vast tapestry of life, the microscopic realm holds immense significance. The article titled 'The Microbial World: Unseen Heroes of Life' explores the pivotal role played by microorganisms in both nature and medicine. Delving into the unseen, this piece sheds light on the diverse world of bacteria and fungi, often overlooked yet fundamental to the balance of life.\n" +
                            "\n" +
                            "The microbial world is a fascinating landscape, with intricate interactions and symbiotic relationships that contribute to the health of ecosystems. From nutrient cycling in the soil to the digestive processes within our bodies, microorganisms are the unsung heroes orchestrating vital functions.\n" +
                            "\n" +
                            "Moreover, the article discusses the applications of microbial research in medicine. Scientists, armed with powerful microscopes, delve into the intricate details of microorganisms to understand their potential in treating diseases and developing pharmaceuticals. The exploration of the microbial world opens doors to innovations that impact both human health and the environment.\n" +
                            "\n" +
                            "Through captivating insights and scientific revelations, this article invites readers to appreciate the unseen yet extraordinary contributions of microorganisms to the intricate web of life. It emphasizes the importance of continued research and conservation efforts to unlock the full potential of the microbial world.")
                    .secondaryTitle("Exploring the Role of Microorganisms in Nature and Medicine")
                    .mainImg("https://images.pexels.com/photos/751682/pexels-photo-751682.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Red, White and Green Chameleon")
                    .mainImgCredit("Pexels, Egor Kamelev")
                    .secondImg("https://images.pexels.com/photos/706924/pexels-photo-706924.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Red Jelly Fish")
                    .secondImgCredit("Pexels, Gabriel Peter")
                    .build();

            Article entity24 = modelMapper.map(dto24, Article.class);
            entity24.setUser(user);
            entity24.setDate(LocalDate.now());
            articleRepository.save(entity24);

            ArticleRequestDto dto25 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("Genetic Diversity: The Engine of Evolution")
                    .content("Exploring the intricate dance of life, the article titled 'Genetic Diversity: The Engine of Evolution' delves into the profound importance of genetic variability in shaping the natural world. At the heart of evolution lies the diverse tapestry of genes, influencing the survival and adaptation of species.\n" +
                            "\n" +
                            "The article guides readers through the fascinating realm of genetics, using captivating language to unravel the complexities of DNA strands and the significance of genetic diversity. Illustrating the concept through vivid imagery, it provides a visual journey into the core mechanisms that drive the engine of evolution.\n" +
                            "\n" +
                            "Understanding the importance of genetic variability is paramount in appreciating the resilience and adaptability of life forms. The exploration of population genetics, depicted in the article, showcases ongoing studies that unravel the secrets of how genetic diversity influences the dynamics of species over time.\n" +
                            "\n" +
                            "With a focus on both the scientific intricacies and real-world applications, this piece sparks curiosity about the role genetics plays in the survival of species. It highlights the ongoing efforts of scientists and researchers, illustrated by images of genetic studies in progress, to deepen our understanding of the engine that powers the remarkable diversity of life on Earth.")
                    .secondaryTitle("Understanding the Importance of Genetic Variability")
                    .mainImg("https://images.pexels.com/photos/950327/pexels-photo-950327.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Jewel Beetle on Tree Branch")
                    .mainImgCredit("Pexels, Bambang Suryadi")
                    .secondImg("https://images.pexels.com/photos/633437/pexels-photo-633437.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Brown and Black Butterfly Perched on Yellow and Red Petaled Flower Closeup Photography")
                    .secondImgCredit("Leonardo Jarro")
                    .build();

            Article entity25 = modelMapper.map(dto25, Article.class);
            entity25.setUser(user);
            entity25.setDate(LocalDate.now());
            articleRepository.save(entity25);

            ArticleRequestDto dto26 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("Adaptation Strategies in Flora and Fauna")
                    .content("Exploring the wonders of the natural world, the article titled 'Adaptation Strategies in Flora and Fauna' delves into the intricate survival tactics employed by plants and animals in response to environmental changes. This captivating piece sheds light on the remarkable ways in which life forms have evolved to thrive in diverse climates.\n" +
                            "\n" +
                            "The article unfolds the captivating strategies employed by fauna, illustrating animals demonstrating camouflage in their natural habitats through vivid descriptions. The visual imagery provided paints a vivid picture of the natural world, where creatures have honed their survival skills to blend seamlessly into their surroundings.\n" +
                            "\n" +
                            "Shifting focus to the flora kingdom, the article explores plants exhibiting adaptive features in different climates. Through detailed descriptions, it unveils the fascinating ways in which plants have evolved to cope with varying environmental conditions, showcasing nature's resilience and ingenuity.\n" +
                            "\n" +
                            "By delving into the intricate dance between organisms and their environments, the piece highlights the delicate balance of ecosystems. It instills an appreciation for the adaptability of life forms, emphasizing the ongoing processes of evolution that shape the rich biodiversity we observe in the world.")
                    .secondaryTitle("Survival Tactics in Response to Environmental Changes")
                    .mainImg("https://images.pexels.com/photos/459449/pexels-photo-459449.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Blue Bird Sits on Tree Branch")
                    .mainImgCredit("Pixabay")
                    .secondImg("https://images.pexels.com/photos/1522160/pexels-photo-1522160.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Photo of a Fish on Corals")
                    .secondImgCredit("Tom Fisk")
                    .build();

            Article entity26 = modelMapper.map(dto26, Article.class);
            entity26.setUser(user);
            entity26.setDate(LocalDate.now());
            articleRepository.save(entity26);

            ArticleRequestDto dto27 = ArticleRequestDto.builder()
                    .category("Biology")
                    .title("The Symbiotic Dance: Partnerships in Nature")
                    .content("In the intricate tapestry of nature, the article titled 'The Symbiotic Dance: Partnerships in Nature' unfolds the captivating narratives of mutualistic relationships among organisms. This exploration delves into the intricate connections between different species, showcasing the remarkable partnerships that contribute to the resilience and biodiversity of ecosystems.\n" +
                            "\n" +
                            "Through vivid descriptions, the article illustrates various symbiotic relationships with a rich tapestry of examples. From the fascinating alliances between plants and pollinators to the interdependence of predators and prey, the intricate dance of symbiosis is brought to life.\n" +
                            "\n" +
                            "The main focus of the article is a vibrant illustration depicting various symbiotic relationships. This visual representation serves as a compelling guide, offering insights into the diverse ways in which organisms collaborate for shared benefits. It visually captures the essence of these partnerships, portraying the interconnected web of life.\n" +
                            "\n" +
                            "The second part of the article provides a closer look at specific instances of symbiotic interactions between species. Through detailed descriptions, readers gain a deeper understanding of how these relationships unfold in nature, highlighting the delicate balance that sustains ecosystems.\n" +
                            "\n" +
                            "By unraveling the secrets of symbiosis, the article emphasizes the interconnectedness of life forms and the significance of these partnerships in maintaining the health and functionality of diverse ecosystems. It invites readers to appreciate the beauty of cooperation in nature and fosters a deeper understanding of the harmonious relationships that shape the living world.")
                    .secondaryTitle("Mutualistic Relationships Among Organisms")
                    .mainImg("https://images.pexels.com/photos/799308/pexels-photo-799308.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Close Up Photo of Yellow and Black Wasp")
                    .mainImgCredit("Pexels, Egor Kamelev")
                    .secondImg("https://images.pexels.com/photos/2092041/pexels-photo-2092041.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Close-Up Photo of Green Frog")
                    .secondImgCredit("Pexels, Lexo Salazar")
                    .build();

            Article entity27 = modelMapper.map(dto27, Article.class);
            entity27.setUser(user);
            entity27.setDate(LocalDate.now());
            articleRepository.save(entity27);

            ArticleRequestDto dto28 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("The Quantum Universe: Exploring Subatomic Realms")
                    .content("Embark on a fascinating journey into the mysterious depths of the cosmos with the article titled 'The Quantum Universe: Exploring Subatomic Realms.' This exploration takes readers on captivating adventures in the realms of quantum mechanics and particle physics, unraveling the enigmatic nature of subatomic particles.\n" +
                            "\n" +
                            "The article commences with a vivid description of an artistic representation portraying subatomic particles in motion. This imaginative illustration serves as a visual gateway into the quantum universe, capturing the ethereal dance of particles that defy classical understanding. It sets the stage for readers to immerse themselves in the peculiarities of the quantum realm.\n" +
                            "\n" +
                            "As the narrative unfolds, attention shifts to a second image—an intriguing depiction of a particle collider experiment capturing elusive particles. This experimental snapshot provides a glimpse into the sophisticated tools and technologies scientists employ to study the quantum world. The image serves as a testament to the relentless pursuit of knowledge, highlighting the dedication of physicists in their quest to unravel the secrets of subatomic realms.\n" +
                            "\n" +
                            "The heart of the article explores the adventures in quantum mechanics and particle physics, delving into the fundamental principles that govern the behavior of particles at the quantum level. From the mind-bending concept of superposition to the fascinating world of quantum entanglement, readers are guided through the perplexing but enthralling terrain of quantum phenomena.\n" +
                            "\n" +
                            "Through engaging descriptions and accessible language, the article invites both enthusiasts and novices alike to contemplate the profound implications of quantum mechanics. It sparks curiosity about the nature of reality at its most fundamental level and underscores the relentless pursuit of understanding that drives physicists to explore the quantum universe.\n" +
                            "\n" +
                            "In conclusion, 'The Quantum Universe' offers a thought-provoking glimpse into the subatomic realms, leaving readers with a sense of wonder and appreciation for the complexity and beauty inherent in the quantum fabric of our universe.")
                    .secondaryTitle("Adventures in Quantum Mechanics and Particle Physics")
                    .mainImg("https://images.pexels.com/photos/6238297/pexels-photo-6238297.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Equations Written On Blackboard")
                    .mainImgCredit("Pexels, Monstera Production")
                    .secondImg("https://images.pexels.com/photos/3862130/pexels-photo-3862130.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Woman Writing On A Whiteboard")
                    .secondImgCredit("Pexels, ThisIsEngineering")
                    .build();

            Article entity28 = modelMapper.map(dto28, Article.class);
            entity28.setUser(user);
            entity28.setDate(LocalDate.now());
            articleRepository.save(entity28);

            ArticleRequestDto dto29 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("Einstein's Dream: Understanding the Theory of Relativity")
                    .content("Embark on a captivating exploration of the cosmos with the article titled 'Einstein's Dream: Understanding the Theory of Relativity.' This article delves into the revolutionary concepts of space-time and gravity, unraveling the profound ideas conceived by the iconic physicist Albert Einstein.\n" +
                            "\n" +
                            "The journey begins with an evocative illustration depicting the curvature of space-time around massive objects. This visual representation serves as a gateway into the intricate world of Einstein's theories, where the fabric of space and time is not a static backdrop but a dynamic entity influenced by the presence of mass. The image sets the stage for readers to delve into the mind-expanding concepts that redefine our understanding of the cosmos.\n" +
                            "\n" +
                            "The narrative then transitions to a second image—an observatory capturing gravitational waves from cosmic events. This image showcases the cutting-edge technologies employed by scientists to detect the ripples in space-time predicted by Einstein's general theory of relativity. It underscores the continuous pursuit of knowledge and the innovative methods used to explore the cosmic phenomena Einstein's theories predict.\n" +
                            "\n" +
                            "The core of the article unfolds as it demystifies the complex yet awe-inspiring principles of relativity. From the fundamental postulates of special relativity to the profound insights of general relativity, readers are guided through the intellectual landscape that reshaped our understanding of the universe. Concepts such as time dilation, warped space-time, and the equivalence principle come to life through accessible explanations and vivid descriptions.\n" +
                            "\n" +
                            "Through engaging storytelling, the article invites readers to ponder the implications of Einstein's dream—a vision that transformed our cosmic worldview. It sparks curiosity about the interconnectedness of space and time, inviting contemplation on the fabric of the universe and the forces that govern its behavior.\n" +
                            "\n" +
                            "In conclusion, 'Einstein's Dream' offers a captivating journey through the concepts of the theory of relativity, leaving readers with a newfound appreciation for the brilliance of Einstein's contributions and the enduring impact of his visionary ideas on our understanding of the cosmos.")
                    .secondaryTitle("Grasping the Concepts of Space-Time and Gravity")
                    .mainImg("https://images.pexels.com/photos/714699/pexels-photo-714699.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Person Holding a Chalk in Front of the Chalk Board")
                    .mainImgCredit("JESHOOTS.com")
                    .secondImg("https://images.pexels.com/photos/60582/newton-s-cradle-balls-sphere-action-60582.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Gray Newton's Cradle in Close-up Photogaphy\n")
                    .secondImgCredit("Pixabay")
                    .build();

            Article entity29 = modelMapper.map(dto29, Article.class);
            entity29.setUser(user);
            entity29.setDate(LocalDate.now());
            articleRepository.save(entity29);


            ArticleRequestDto dto30 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("Cosmic Forces: The Dynamics of the Universe")
                    .content("Embark on a riveting journey through the expansive realms of physics with the article titled 'Cosmic Forces: The Dynamics of the Universe.' This exploration delves into the fundamental forces that govern the cosmos, unraveling the intricate dance of gravity, electromagnetism, and the strong and weak forces that shape the fabric of the universe.\n" +
                            "\n" +
                            "The odyssey commences with an evocative image—an artistic representation of cosmic forces at play. This visual masterpiece serves as a captivating introduction to the vast and interconnected forces that drive the dynamics of celestial bodies. The image ignites curiosity, setting the stage for an in-depth exploration of the cosmic dance that unfolds across the universe.\n" +
                            "\n" +
                            "Transitioning to the second image, we find a scientist engrossed in the study of electromagnetic phenomena in a laboratory. This image offers a glimpse into the world of scientific inquiry, where researchers seek to understand and harness the forces that govern both the smallest particles and the grandest cosmic structures. It highlights the human quest for knowledge and the tools employed to unravel the mysteries of the universe.\n" +
                            "\n" +
                            "The heart of the article unveils the profound principles underlying cosmic forces. From the universal pull of gravity to the intricate dance of charged particles guided by electromagnetism, readers are guided through the fundamental laws that govern the behavior of matter and energy. The exploration extends to the strong and weak nuclear forces, revealing the forces that bind atomic nuclei and govern the processes within stars.\n" +
                            "\n" +
                            "Throughout the narrative, the article weaves together accessible explanations and vivid descriptions, making complex physics concepts comprehensible and engaging. The interconnected nature of these cosmic forces is underscored, emphasizing their role in shaping the cosmos from the grandest cosmic scales to the subatomic realms.\n" +
                            "\n" +
                            "In conclusion, 'Cosmic Forces' invites readers to marvel at the elegance of the universe's dynamics. It sparks wonder about the forces that orchestrate the celestial ballet and encourages contemplation on the profound interplay of fundamental forces that define the very fabric of our cosmic existence.")
                    .secondaryTitle("Exploring Gravity, Electromagnetism, and Weak and Strong Forces")
                    .mainImg("https://images.pexels.com/photos/1205301/pexels-photo-1205301.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Starry Night Sky")
                    .mainImgCredit("Pexels, Felix Mittermeier")
                    .secondImg("https://images.pexels.com/photos/2098427/pexels-photo-2098427.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Brown Rocky Mountain Photography")
                    .secondImgCredit("Pexels, eberhard grossgasteiger")
                    .build();


            Article entity30 = modelMapper.map(dto30, Article.class);
            entity30.setUser(user);
            entity30.setDate(LocalDate.now());
            articleRepository.save(entity30);

            ArticleRequestDto dto31 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("Beyond the Higgs Boson: Unraveling the Secrets of Particle Physics")
                    .content("Embark on a scientific odyssey into the microscopic world of subatomic particles with the enthralling article, 'Beyond the Higgs Boson: Unraveling the Secrets of Particle Physics.' This exploration delves into the cutting-edge discoveries that propel our understanding of the fundamental constituents of the universe.\n" +
                            "\n" +
                            "The journey begins with a visually stunning main image—an artistic depiction of a particle collision creating a burst of exotic particles. This captivating illustration captures the dynamic and energetic nature of particle interactions, setting the stage for a deep dive into the realm where the smallest building blocks of matter reveal their secrets.\n" +
                            "\n" +
                            "Transitioning to the second image, we encounter a particle physicist immersed in the analysis of experimental results. This image provides a glimpse into the meticulous work conducted in laboratories worldwide, where scientists scrutinize data to uncover the behavior and properties of elusive subatomic particles. It emphasizes the dedication and precision required in the pursuit of unraveling the mysteries concealed within the quantum realm.\n" +
                            "\n" +
                            "The article unfolds with a detailed exploration of recent discoveries in particle physics. From the groundbreaking identification of the Higgs Boson to the ongoing quests for new particles beyond the Standard Model, readers are guided through the intricacies of experiments conducted at particle accelerators. The narrative demystifies complex concepts, making the microscopic world accessible to readers keen on understanding the forefront of particle physics.\n" +
                            "\n" +
                            "Throughout the article, the emphasis is on the collaborative efforts of physicists and researchers working at the cutting edge of science. The storytelling weaves together the history, challenges, and triumphs of particle physics, conveying the excitement and significance of unraveling the secrets concealed within the subatomic fabric of reality.\n" +
                            "\n" +
                            "In conclusion, 'Beyond the Higgs Boson' invites readers to marvel at the continuous quest for knowledge in the realm of particle physics. It underscores the significance of these discoveries in reshaping our understanding of the universe and highlights the tireless dedication of scientists committed to unraveling the secrets that lie beyond the known boundaries of the subatomic world.")
                    .secondaryTitle("Discoveries in the Microscopic World of Subatomic Particles")
                    .mainImg("https://images.pexels.com/photos/13014236/pexels-photo-13014236.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Red Green and Blue Balloons")
                    .mainImgCredit("Pexels, Steve Johnson")
                    .secondImg("https://images.pexels.com/photos/73909/nuclear-weapons-test-nuclear-weapon-weapons-test-explosion-73909.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Grayscale Photo of Explosion on the Beach\n")
                    .secondImgCredit("Pixabay")
                    .build();

            Article entity31 = modelMapper.map(dto31, Article.class);
            entity31.setUser(user);
            entity31.setDate(LocalDate.now());
            articleRepository.save(entity31);


            ArticleRequestDto dto32 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("The Mysteries of Dark Energy: Expanding Horizons of the Cosmos")
                    .content("Embark on a cosmic journey with the captivating article, 'The Mysteries of Dark Energy: Expanding Horizons of the Cosmos.' This exploration delves into the enigmatic forces driving the acceleration of the universe, challenging our understanding of the cosmos.\n" +
                            "\n" +
                            "The narrative unfolds against the backdrop of an intriguing main image—an artistic illustration depicting the mysterious force of dark energy. This visual representation captures the elusive and ethereal nature of dark energy, setting the tone for an exploration of the cosmic forces that govern the vast expanse of the universe.\n" +
                            "\n" +
                            "Transitioning to the second image, we witness astronomers immersed in the observation of distant galaxies affected by dark energy. This image provides a glimpse into the profound observational efforts undertaken by astronomers to unravel the impact of dark energy on the cosmic landscape. It underscores the collaborative endeavors of scientists worldwide to comprehend the mysteries concealed within the cosmic expanses.\n" +
                            "\n" +
                            "The article progresses with a detailed examination of dark energy, delving into the historical context of its discovery and the ongoing efforts to understand its nature. From the cosmic microwave background to the large-scale structure of the universe, readers are guided through the observational evidence and theoretical frameworks that underpin our current understanding of dark energy.\n" +
                            "\n" +
                            "Throughout the narrative, the emphasis is on the dynamic interplay between theoretical predictions and observational data. The storytelling weaves together the evolving theories surrounding dark energy, from its initial proposal to the latest advancements in observational cosmology. The article aims to demystify complex astrophysical concepts, offering readers a comprehensive overview of the current state of dark energy research.\n" +
                            "\n" +
                            "In conclusion, 'The Mysteries of Dark Energy' invites readers to contemplate the unknown forces shaping the destiny of the universe. It highlights the ongoing scientific endeavors to unlock the secrets of dark energy, emphasizing the collaborative spirit of the global scientific community in pushing the boundaries of our cosmic understanding.")
                    .secondaryTitle("Probing the Unknown Forces Driving the Universe's Acceleration")
                    .mainImg("https://images.pexels.com/photos/167843/milky-way-rocks-night-landscape-167843.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Rock Formation during Night Time")
                    .mainImgCredit("Pixabay")
                    .secondImg("https://images.pexels.com/photos/2098428/pexels-photo-2098428.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Canion Mountains on Night Sky")
                    .secondImgCredit("eberhard grossgasteiger")
                    .build();

            Article entity32 = modelMapper.map(dto32, Article.class);
            entity32.setUser(user);
            entity32.setDate(LocalDate.now());
            articleRepository.save(entity32);


            ArticleRequestDto dto33 = ArticleRequestDto.builder()
                    .category("Physics")
                    .title("Revolutionizing Teleportation: Quantum Entanglement and Instantaneous Travel")
                    .content("Embark on a thrilling exploration of the future with the article, 'Revolutionizing Teleportation: Quantum Entanglement and Instantaneous Travel.' This article delves into the groundbreaking possibilities offered by quantum principles, particularly quantum entanglement, and their application in teleportation.\n" +
                            "\n" +
                            "The narrative unfolds against the backdrop of an intriguing main image—an artistic representation of quantum entanglement for teleportation. This visual sets the stage for the futuristic and revolutionary theme of the article, capturing the essence of harnessing quantum phenomena for instantaneous travel.\n" +
                            "\n" +
                            "Transitioning to the second image, we peer into the world of a quantum physicist conducting experiments on entangled particles. This image provides a glimpse into the cutting-edge research and experimentation underway in the field of quantum teleportation. It underscores the meticulous efforts of scientists pushing the boundaries of our understanding of quantum mechanics.\n" +
                            "\n" +
                            "The article progresses with a detailed exploration of the fundamental principles of quantum entanglement and its potential applications in teleportation. From the concept of quantum superposition to the intricate dance of entangled particles, readers are guided through the fascinating world of quantum physics. The storytelling weaves together theoretical frameworks and experimental breakthroughs, offering readers a comprehensive overview of the current state of teleportation research.\n" +
                            "\n" +
                            "Throughout the narrative, the emphasis is on the transformative impact that quantum teleportation could have on travel and communication. The article speculates on the possibilities of instantaneous transportation of information and matter, sparking the imagination and curiosity of readers about the potential future advancements in teleportation technology.\n" +
                            "\n" +
                            "In conclusion, 'Revolutionizing Teleportation' invites readers to contemplate the paradigm-shifting prospects offered by quantum entanglement. It highlights the exciting possibilities that may redefine our understanding of space and time, emphasizing the role of ongoing scientific research in unlocking the mysteries of quantum physics for real-world applications.")
                    .secondaryTitle("Harnessing Quantum Principles for Teleportation Applications")
                    .mainImg("https://images.pexels.com/photos/2312040/pexels-photo-2312040.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Milky Way Photography")
                    .mainImgCredit("Pexels, Nicole Avagliano")
                    .secondImg("https://images.pexels.com/photos/821644/pexels-photo-821644.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Photo of Galaxy")
                    .secondImgCredit("Pexels, Alex Andrews")
                    .build();

            Article entity33 = modelMapper.map(dto33, Article.class);
            entity33.setUser(user);
            entity33.setDate(LocalDate.now());
            articleRepository.save(entity33);


            ArticleRequestDto dto34 = ArticleRequestDto.builder()
                    .category("Chemistry")
                    .title("Nanotechnology: The Revolutionary World of Molecular Engineering")
                    .content("Embark on a journey into the revolutionary world of molecular engineering with the article, 'Nanotechnology: The Revolutionary World of Molecular Engineering.' This captivating exploration in the field of chemistry unravels the applications and innovations that emerge at the nano scale, offering readers a glimpse into the transformative landscape of nanotechnology.\n" +
                            "\n" +
                            "The article opens with an eye-catching main image—an illustration showcasing the diverse applications of nanomaterials. This visual immediately captivates the audience, setting the tone for a narrative that delves into the myriad ways in which molecular engineering at the nano scale is reshaping industries and technologies.\n" +
                            "\n" +
                            "Transitioning to the second image, we witness chemists immersed in the intricate world of molecular manipulation in a nanotechnology lab. This image provides a behind-the-scenes look at the hands-on work conducted by scientists, emphasizing the precision and skill required to engineer materials at such a minuscule scale.\n" +
                            "\n" +
                            "The article's content seamlessly integrates with these visuals, exploring the foundational principles of nanotechnology and its wide-ranging applications. From advancements in medicine and electronics to environmental remediation, readers are guided through the vast potential of molecular engineering. The storytelling emphasizes the interdisciplinary nature of nanotechnology, where chemistry converges with physics, biology, and engineering to unlock new possibilities.\n" +
                            "\n" +
                            "Throughout the narrative, the focus remains on real-world innovations and breakthroughs, showcasing how nanotechnology is not merely theoretical but actively contributing to advancements that impact our daily lives. The article encourages readers to contemplate the ethical considerations and potential societal impacts of these emerging technologies.\n" +
                            "\n" +
                            "In conclusion, 'Nanotechnology' invites readers to ponder the profound implications of molecular engineering at the nano scale. It emphasizes the role of chemistry in driving innovation and progress in this dynamic field, underscoring the collaborative efforts of scientists and researchers to push the boundaries of what is possible in the world of nanotechnology.")
                    .secondaryTitle("Applications and Innovations at the Nano Scale")
                    .mainImg("URL_TO_CHEMISTRY_IMAGE_7")
                    .mainImgDescription("Illustration of nanomaterials and their diverse applications")
                    .mainImgCredit("Image by NanoTechIllustrator")
                    .secondImg("https://images.pexels.com/photos/1366942/pexels-photo-1366942.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Photo of Clear Glass Measuring Cup Lot")
                    .secondImgCredit("Pexels, Rodolfo Clix")
                    .build();

            Article entity34 = modelMapper.map(dto34, Article.class);
            entity34.setUser(user);
            entity34.setDate(LocalDate.now());
            articleRepository.save(entity34);

            ArticleRequestDto dto35 = ArticleRequestDto.builder()
                    .category("Chemistry")
                    .title("Green Chemistry: Sustainable Practices for a Healthier Planet")
                    .content("Dive into the realm of sustainable practices with the article 'Green Chemistry: Sustainable Practices for a Healthier Planet.' This exploration in the field of chemistry sheds light on eco-friendly approaches to chemical synthesis and production, showcasing how innovative solutions are driving positive change for both the industry and the environment.\n" +
                            "\n" +
                            "The article's main image immediately draws attention—a depiction of chemical reactions designed for minimal environmental impact. This visual sets the stage for a narrative that emphasizes the importance of adopting greener alternatives in chemical processes to mitigate ecological harm.\n" +
                            "\n" +
                            "The second image transports readers into a green chemistry lab, where chemists are actively engaged in developing sustainable materials. This image serves as a powerful testament to the hands-on efforts of scientists working towards a healthier planet. It complements the article's content by providing a tangible glimpse into the laboratories where sustainable innovations are conceived and refined.\n" +
                            "\n" +
                            "The content of the article intricately weaves through the principles of green chemistry, highlighting how sustainable practices are being integrated into various aspects of chemical synthesis and production. From the design of environmentally friendly reactions to the development of renewable materials, readers are guided through the transformative potential of green chemistry.\n" +
                            "\n" +
                            "Throughout the narrative, the article emphasizes the global impact of adopting sustainable practices in the field of chemistry. It delves into real-world examples where green chemistry is making a difference, from reducing carbon footprints to minimizing waste. The storytelling encourages readers to reflect on their roles in fostering a sustainable future and the collective responsibility of the scientific community.\n" +
                            "\n" +
                            "In conclusion, 'Green Chemistry' inspires readers to consider the profound implications of adopting eco-friendly approaches in the realm of chemistry. It underscores the critical role of sustainable practices in shaping a healthier planet, fostering a sense of responsibility, and encouraging ongoing efforts towards a greener, more sustainable future.")
                    .secondaryTitle("Eco-Friendly Approaches to Chemical Synthesis and Production")
                    .mainImg("https://images.pexels.com/photos/220989/pexels-photo-220989.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .mainImgDescription("Water Bubbles")
                    .mainImgCredit("Pixabay")
                    .secondImg("https://images.pexels.com/photos/276205/pexels-photo-276205.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
                    .secondImgDescription("Spider Web With Dew Vector Art")
                    .secondImgCredit("Pixabay")
                    .build();

            Article entity35 = modelMapper.map(dto35, Article.class);
            entity35.setUser(user);
            entity35.setDate(LocalDate.now());
            articleRepository.save(entity35);


        }
    }
}
