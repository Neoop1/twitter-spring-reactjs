import {makeStyles, Theme} from "@material-ui/core";

export const useFollowButtonStyles = makeStyles((theme: Theme) => ({
    outlinedButton: {
        width: 79,
        '&:hover': {
            backgroundColor: theme.palette.secondary.light,
        },
    },
}));
