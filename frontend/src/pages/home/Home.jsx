import styles from "./Homestyles.module.css";

const Home = () => {

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
                            <button className={styles.difficultybutton}>
                                <div className={styles.difficultylabel}>Easy</div>
                                <div className={styles.difficultydesc}>3-5 letters</div>
                            </button >
                            <button className={styles.difficultybutton}>
                                <div className={styles.difficultylabel}>Medium</div>
                                <div className={styles.difficultydesc}>6-8 letters</div>
                            </button>
                            <button className={styles.difficultybutton}>
                                <div className={styles.difficultylabel}>Hard</div>
                                <div className={styles.difficultydesc}>9+ letters</div>
                            </button>
                        </div>
                    </section>

                    <section className={styles.section}>
                        <h2 className={styles.sectiontitle}>Category</h2>
                        <div className={styles.selectcontainer}>
                            <select id="categorySelect" className={styles.categoryselect} aria-label="Select category">
                                <option value="all">All</option>
                                <option value="animals">Animals</option>
                                <option value="countries">Countries</option>
                                <option value="food">Food</option>
                            </select>
                        </div>
                    </section>

                    <button className={styles.startbutton}>
                        Start Game
                    </button>

                    <div className={styles.actions}>
                        <button className={styles.actionbutton}>
                            Rules
                        </button>
                        <button className={styles.actionbutton}>
                            Settings
                        </button>
                    </div>


                </main>
            </div>
        </div>
    );
};

export default Home;
