import styles from "./Homestyles.module.css";
import {useState} from "react";

const Home = () => {
    const [difficulty, setDifficulty] = useState(null);
    const [category, setCategory] = useState("all");

    return (
        <div className={styles.homePage}>
            <div className={styles.container}>
                <header className={styles.header}>
                    <h1 className={styles.title}>Hangman</h1>
                    <p className={styles.subtitle}>Guess the word, save the stick figure</p>
                </header>

                <main>
                    <section className={styles.section}>
                        <h2 className={styles.sectiontitle}>Difficulty</h2>
                        <div className={styles.difficultyoptions}>
                            <button
                                className={`${styles.difficultybutton} ${difficulty === "easy" ? styles.selected : ""}`}
                                onClick={() => setDifficulty("easy")}
                            >
                                <div className={styles.difficultylabel}>Easy</div>
                                <div className={styles.difficultydesc}>3-5 letters</div>
                            </button>
                            <button
                                className={`${styles.difficultybutton} ${difficulty === "medium" ? styles.selected : ""}`}
                                onClick={() => setDifficulty("medium")}
                            >
                                <div className={styles.difficultylabel}>Medium</div>
                                <div className={styles.difficultydesc}>6-8 letters</div>
                            </button>
                            <button
                                className={`${styles.difficultybutton} ${difficulty === "hard" ? styles.selected : ""}`}
                                onClick={() => setDifficulty("hard")}
                            >
                                <div className={styles.difficultylabel}>Hard</div>
                                <div className={styles.difficultydesc}>9+ letters</div>
                            </button>
                        </div>
                    </section>

                    <section className={styles.section}>
                        <h2 className={styles.sectiontitle}>Category</h2>
                        <div className={styles.selectcontainer}>
                            <select
                                id="categorySelect"
                                className={styles.categoryselect}
                                aria-label="Select category"
                                value={category}
                                onChange={(e) => setCategory(e.target.value)}
                            >
                                <option value="all">All</option>
                                <option value="animals">Animals</option>
                                <option value="countries">Countries</option>
                                <option value="food">Food</option>
                            </select>
                        </div>
                    </section>

                    <button
                        className={styles.startbutton}
                        onClick={() => {
                            if (difficulty) {
                                console.log("Starting game:", difficulty, category);

                            } else {
                                console.log("Please select a difficulty");
                            }
                        }}
                    >
                        Start Game
                    </button>

                    <div className={styles.actions}>
                        <button
                            className={styles.actionbutton}
                            onClick={() => console.log("Rules clicked - TODO")}
                        >
                            Rules
                        </button>
                        <button
                            className={styles.actionbutton}
                            onClick={() => console.log("Settings clicked - TODO")}
                        >
                            Settings
                        </button>
                    </div>


                </main>
            </div>
        </div>
    );
};

export default Home;
