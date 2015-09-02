import Control.Monad
import Data.List
import Data.Maybe
import System.IO

-- return a list of splits of a string, starting from the right
splits :: String -> [(String, String)]
splits w = reverse $ zip (dropEnds $ inits w) (dropEnds $ tails w)
    where dropEnds xs = drop 1 $ take (length xs - 1) xs

-- given a split (s, t) find smallest char in t that is greater than (last s)
greaterRight :: (String, String) -> Maybe (String, String, Char)
greaterRight (s, t) = case chars of
    [] -> Nothing
    _ -> Just (s, t, minimum chars)
    where chars = [c | c <- t, c > last s]

-- replace first occurrence of c in s with c'
replaceFirst :: Char -> Char -> String -> String
replaceFirst c c' s = beforeC ++ r_ cAndRest
    where (beforeC, cAndRest) = break (==c) s
          r_ [] = []
          r_ (_:rest) = c':rest

-- swap (last s) with c in t and return s ++ sort t
-- swapLast "abcd" "efgh" 'g' => "abcgefdh"
swapLast :: (String, String, Char) -> String
swapLast (s, t, c) =
    take (length s - 1) s ++
    [c] ++
    sort (replaceFirst c (last s) t)

-- Find the lexicographically "next" string that's an anagram
lexNext :: String -> Maybe String
lexNext w = case answers of
    [] -> Nothing
    _ -> Just (head answers)
    where answers = catMaybes $ fmap (liftM swapLast) (map greaterRight $ splits w)

outputFor :: Maybe String -> String
outputFor (Just s) = s
outputFor Nothing = "no answer"

main :: IO ()
main =
    let processWords = do
        eof <- isEOF
        unless eof $ do
            line <- getLine
            putStrLn $ outputFor $ lexNext line
            processWords
    in do
        _ <- getLine    -- number of words, discard
        processWords
